package com.alimoghimi.ezkala.eztask;

import java.io.Serializable;

public class Tasks implements Comparable<Tasks> , Serializable {


        static int numberOfTasks = 0;
        private String title;
        private String Y;
        private String M;
        private String D;
        private String h;
        private String m;
        private String s;
        private String date;
        private long totalD = 0;
        private long totalS = 0;
        private boolean isDated;
        boolean dontConcat = false;
        private int priority; //5 types , 1.not desc , 2.sub , 3.high , 4.med , 5.low

        public Tasks(String massage, String Y, String M, String D, String h, String m, String s, int priority) {

                isDated = true;
                this.title = massage;
                this.Y = Y;
                this.M = M;
                this.D = D;

                if (h.equals("") || m.equals("") || s.equals("")) {

                        if (h.equals("")) {
                                this.h = "0";
                                dontConcat = true;
                        }
                        if (m.equals("")) {
                                this.m = "0";
                                dontConcat = true;
                        }
                        if (s.equals("")) {
                                this.s = "0";
                                dontConcat = true;
                        }
                }

                else {

                        this.h = h;
                        this.m = m;
                        this.s = s;
                }

                this.priority = priority;
                calcTotalD();
                calcTotalS();
                conCatDate();
        }

        Tasks(String title, String date, String year, String month, String day){  // using calander.

                isDated = true;
                this.title = title;
                this.date = date;
                this.Y = year;
                this.M = month;
                this.D = day;
                calcTotalD();
        }

        Tasks(String title, int priority) {

                this.title = title;
                this.priority = priority;
                isDated = false;
                date = "";
                numberOfTasks++;
        }

        @Override
        public boolean equals(Object obj) {
                return (this.date.equals(((Tasks)obj).date) && (this.title.equals(((Tasks)obj).title)));
        }

        public void conCatDate() {

                if (!dontConcat)
                        date = Y + "/" + M + "/" + D + "  " + h + ":" + m + ":" + s;

                else
                        date = Y + "/" + M + "/" + D;
        }

        public String getDate() {
                return date;
        }

        public boolean isDated() {
                return isDated;
        }

        public String getTitle() {
                return title;
        }

        public String getPriority() {
                if(this.priority == 1)
                        return "Not Described";

                if(this.priority == 2)
                        return "Other";

                if(this.priority == 3)
                        return "High Priority";

                if(this.priority == 4)
                        return "Medium Priority";

                if(this.priority == 5)
                        return "Low Priority";

                return "Not Described";
        }

        public void setTitle(String massage) {
                this.title = massage;
        }

        @Override
        public String toString() {

                return title + "\n" + Y + "." + M + "." + D + ".";
        }

        public void calcTotalD() {

                totalD += Integer.parseInt(Y) * 365;
                totalD += Integer.parseInt(M) * 30;
                totalD += Integer.parseInt(D);
        }

        public void calcTotalS() {

                totalS += Integer.parseInt(h) * 3600;
                totalS += Integer.parseInt(m) * 60;
                totalS += Integer.parseInt(s);
        }

        @Override
        public int compareTo(Tasks o) {
                //return (isDated) ? -1 : 1;

                if (isDated)
                {

                        if (this.priority == o.priority)
                        {
                             if (this.totalD > o.totalD)
                             {
                                        return -1;
                                }
                                else if (this.totalD < o.totalD) {
                                   return 1;
                                }

                                else {

                                    if (this.totalS > o.totalS) {
                                          return -1;
                                    }

                                    else if (this.totalS < o.totalS)
                                       return 1;

                                    else return 0;
                                }
                        } else if (this.priority > o.priority)
                                return 1;

                        else return -1;
                }

                else {

                        if (this.priority == o.priority)
                                return 0;

                        else if (this.priority > o.priority)

                                return 1;
                        else return -1;

                }
        }
}

// class subTask extends Tasks{}
