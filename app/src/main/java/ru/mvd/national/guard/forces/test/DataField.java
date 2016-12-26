package ru.mvd.national.guard.forces.test;

public class DataField {

    private String question;
    private String ans1;
    private String ans2;
    private String ans3;
    private String ans4;
    private String rightAns;

    public DataField(String rawString) {
        String[] data = rawString.split("\\*");
        boolean ansCheck = false;
        for (int i = 1; i < data.length && !ansCheck; i++) {
            if (data[i].charAt(0) == '#') {
                ansCheck = true;
                data[i] = data[i].substring(1);
                this.rightAns = data[i];
            }
        }
        this.question = data[0];
        this.ans1 = data[1];
        this.ans2 = data[2];
        this.ans3 = data[3];
        this.ans4 = data[4];
    }

    public String getQuestion() {
        return question;
    }

    public String getAns1() {
        return ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public String getRightAns() {
        return rightAns;
    }

    public String getAns4() {
        return ans4;
    }

}
