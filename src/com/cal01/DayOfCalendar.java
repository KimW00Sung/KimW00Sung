package com.cal01;

public class DayOfCalendar {
    //윤년 계산
    private static boolean isLeapYear(int year){
        boolean leap = false;
        if((year%4==0) && (year%100!=0) || (year%400==0)){
            leap=true;
        }
        return leap;
    }

    private static int getDate(int year,int month){
        int tmp=0;
        switch (month) {
            case 1:case 3:case 5:case 7:case 8:case 10:case 12:
                tmp=31;
                break;
            case 4: case 6: case 9: case 11:
                tmp=30;
                break;
            case 2:
                if(isLeapYear(year)){
                    tmp=29;
                }
                else{tmp=28;}
        }
        return tmp;
    }
    private static int getDayOfWeek(int year,int month){
        int dayOfWeek=0;
        int sum=0;

        for(int i=1;i<year;i++){
            for(int j=1;j<=12;j++){
                sum+=getDate(i,j);//(year,month)
            }
        }
        for(int k=1;k<month;k++){
            sum+=getDate(year,k);//이전달까지 Date합
        }
        sum+=1;//알고자하는 Month's start Day
        dayOfWeek=sum%7;
        return dayOfWeek;
    }
    public static void prn(int year,int month){
        System.out.printf("\t\t%d년 %d월\n",year,month);
        System.out.printf("일\t월\t화\t수\t목\t금\t토\n");

        int start=getDayOfWeek(year,month);//DayOfCalendar.getDayOfWeek(year,month);Month's startDay
        int last=getDate(year,month);//Month의 lastDate
        for(int i=1;i<=start;i++){
            System.out.print("\t");
        }

        //print Calendar
        for(int i=1;i<=last;i++){
            System.out.printf("%2d\t",i);
            start++;
            if(start%7==0){
                System.out.println();
            }
        }
    }
}
