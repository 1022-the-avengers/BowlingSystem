package top.arron206.controller.ScoreSimulation;

import top.arron206.controller.GUIController.MainControl;
import top.arron206.model.CompetitionInfo;
import top.arron206.model.Group;
import top.arron206.model.Member;

import java.util.*;

public class CompetitionSimulation {
    private int playerAmount = 60;
    private ArrayList<Member> memberList = new ArrayList<>();
    private PerRoundSimulator perRoundSimulator = new PerRoundSimulator();
    private LinkedList<Integer> totalScore = new LinkedList<>();
    //本次比赛选手实力情况
    private ArrayList<Integer> playersLevel = new ArrayList<>();

    public CompetitionSimulation() {
        initializeMemberInfo();
        setPlayLevel();
    }

    public CompetitionSimulation(int playerAmount) {
        setPlayerAmount(playerAmount);
        setPlayLevel();
        initializeMemberInfo();
    }

    private void setPlayLevel() {
        for (int i = 0; i < playerAmount; ++i)
            playersLevel.add(RandInteger.uniformRand(1, 11));
    }

    public void divideGroup(CompetitionType competitionType) {
        if (competitionType != CompetitionType.Singles) {
            LinkedList<Integer> allGroupsList;
            LinkedList<LinkedList<Integer>> allMemberList;
            Group.addGroup(competitionType.toString());//创建组别
            allGroupsList = DivideGroupSimulation.getOrderIntegerList(playerAmount / competitionType.getAmount());
            allMemberList = DivideGroupSimulation.getAllMembersList(playerAmount / competitionType.getAmount(), competitionType.getAmount());
            Group.addGroupList(allMemberList, allGroupsList, competitionType.toString());
            memberList.clear();
            Member.getAllMembers(memberList);//更新成员信息
        }
    }

    public void classicCompetition() {
        int[] resultArray1, resultArray2;
        int[] descriptions = new int[240];
        int[] fouls = new int[240];
        ArrayList<Member> classicList = new ArrayList<>();
        Member.getRank(classicList);
        int credits[] = new int[16];
        for (int num1 = 0; num1 < 16; ++num1) {
            for (int num2 = num1; num2 < 16; ++num2) {
                if (num1 == num2)
                    continue;
                perRoundSimulator.start(classicList.get(num1).getId(), getPlayLevel(classicList.get(num1)), 1);
                resultArray1 = perRoundSimulator.getResultArray();
                descriptions[num1 * 15 + num2 - 1] = resultArray1[0];
                fouls[num1 * 15 + num2 - 1] = resultArray1[1];
                perRoundSimulator.start(classicList.get(num2).getId(), getPlayLevel(classicList.get(num2)), 1);
                resultArray2 = perRoundSimulator.getResultArray();
                descriptions[num2 * 15 + num1] = resultArray2[0];
                fouls[num2 * 15 + num1] = resultArray2[1];
                if (resultArray1[2] > resultArray2[2])
                    credits[num1]++;
                else
                    credits[num2]++;
            }
        }
        CompetitionInfo.insertAllClassic(getClassicIdList(classicList), arrayToLinkedList(descriptions), arrayToLinkedList(fouls));
        Member.insertAllCredit(getClassicIdList(classicList), arrayToLinkedList(credits));
    }

    private LinkedList<Integer> arrayToLinkedList(int [] array) {
        LinkedList<Integer> arrayList = new LinkedList<>();
        for (int e : array)
            arrayList.add(e);
        return arrayList;
    }

    private LinkedList<Integer> getClassicIdList(ArrayList<Member> classicList) {
        LinkedList<Integer> classicNumberList = new LinkedList<>();
        for (Member e : classicList)
            classicNumberList.add(e.getId());
        return classicNumberList;
    }

    public void ordinaryCompetition(CompetitionType competitionType) {
        int[] resultArray;
        LinkedList<Integer> descriptions = new LinkedList<>();
        LinkedList<Integer> fouls = new LinkedList<>();
        LinkedList<Integer> allGroupScoreList = DivideGroupSimulation.getOrderIntegerList(playerAmount / competitionType.getAmount());
        LinkedList<int[]> perRoundScoreList = new LinkedList<>();
        for (Member e : memberList) {
            for (int i = 1; i <= 6; ++i) {
                perRoundSimulator.start(e.getId(), getPlayLevel(e), i);//生成每局比赛信息
                resultArray = perRoundSimulator.getResultArray();
                descriptions.add(resultArray[0]);
                fouls.add(resultArray[1]);
                totalScore.set(e.getId() - 1, totalScore.get(e.getId() - 1) + resultArray[2]);
                if (competitionType != CompetitionType.Singles)
                    allGroupScoreList.set(getGroupId(competitionType, e) - 1, (allGroupScoreList.get(getGroupId(competitionType, e) - 1) + resultArray[2]));//团队总分累加
                perRoundScoreList.add(perRoundSimulator.getResArray());
            }
        }
        Member.addAllRes(perRoundScoreList, competitionType.toString());
        if (competitionType == CompetitionType.Penta || competitionType == CompetitionType.Singles)//最后一场普通赛写入总分
            Member.updateAllScore(DivideGroupSimulation.getOrderIntegerList(playerAmount), totalScore);
        CompetitionInfo.insertList(competitionType.toString(), descriptions, fouls);
        int i = 0;
        if (competitionType != CompetitionType.Singles)
           i =  Group.updateTotalScoreList(allGroupScoreList, DivideGroupSimulation.getOrderIntegerList(playerAmount / competitionType.getAmount()),competitionType.toString());
        System.out.println("");
    }

    private int getGroupId(CompetitionType competitionType, Member member) {
        switch (competitionType) {
            case Doubles:
                return member.getTeamIdDouble();
            case Triples:
                return member.getTeamIdTriple();
            case Penta:
                return member.getTeamIdPenta();
        }
        return 0;
    }

    private int getPlayLevel(Member member) {
        return playersLevel.get(member.getId() - 1);
    }
    private void initializeMemberInfo() {
        for (int i = 0; i < playerAmount; ++i)
            totalScore.add(0);
        Member.insertAllMember(BasicInfoGenerator.getNameList(playerAmount), BasicInfoGenerator.getProvinceList(playerAmount));
        Member.getAllMembers(memberList);//更新成员信息
    }

    public ArrayList<Member> getMemberList() {
        return memberList;
    }

    public void setPlayerAmount(int playerAmount) {
        this.playerAmount = playerAmount;
    }


}
