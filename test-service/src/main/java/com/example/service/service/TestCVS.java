package com.example.service.service;

import com.example.service.dao.TwMapper;
import com.example.common.entry.tw.Tw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestCVS {

    /*
    @Autowired
    private TwMapper twMapper;

     */

    @Autowired
    private TwMapper twMapper;

    public void temp(String[] args) throws IOException {
        File csv = new File("D:\\2.csv");  // CSV文件路径
        BufferedReader br = null;
        try {
            //br = new BufferedReader(new FileReader(csv));
            br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\\\1.csv"),"UTF8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = "";
        String everyLine = "";

        int i=0;

        try {
            List<String> allString = new ArrayList<>();
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                everyLine = line;
                    /*
                    if(i==3)
                    {
                        System.out.println(everyLine);
                    }
                     */
                //System.out.println(everyLine);
                allString.add(everyLine);

                    /*
                    i++;
                    if(i%100000 == 0)
                    {
                        allString.clear();
                        break;
                    }

                     */

            }



            String oldString="";
            Tw tw;
            //int idCount=1;
            int temp=0;
            boolean isContinue=true;
            for(String s:allString)
            {

                temp++;

                /*
                if(isContinue && s.indexOf("862574394815770624")>-1)
                {
                    continue;
                }
                else
                {
                    isContinue=false;
                }

                 */

                String[] arr=s.split("\",");
                if(arr.length<31)
                {
                    oldString+=s;
                }
                else
                {

                    tw=new Tw();
                    //tw.setId(idCount);
                    tw.setTweetid(arr[0]);
                    tw.setUserid(arr[1]);
                    tw.setUserDisplayName(arr[2]);
                    tw.setUserScreenName(arr[3]);
                    tw.setUserReportedLocation(arr[4]);
                    tw.setUserProfileDescription(arr[5]);
                    tw.setUserProfileUrl(arr[6]);
                    tw.setFollowerCount(arr[7]);
                    tw.setFollowingCount(arr[8]);
                    tw.setAccountCreationDate(arr[9]);
                    tw.setAccountLanguage(arr[10]);
                    tw.setTweetLanguage(arr[11]);
                    tw.setTweetText(arr[12]);
                    tw.setTweetTime(arr[13]);
                    tw.setTweetClientName(arr[14]);
                    tw.setInReplyToUserid(arr[15]);
                    tw.setInReplyToTweetid(arr[16]);
                    tw.setQuotedTweetTweetid(arr[17]);
                    tw.setIsRetweet(arr[18]);
                    tw.setRetweetUserid(arr[19]);
                    tw.setRetweetTweetid(arr[20]);
                    tw.setLatitude(arr[21]);
                    tw.setLongitude(arr[22]);
                    tw.setQuoteCount(arr[23]);
                    tw.setReplyCount(arr[24]);
                    tw.setLikeCount(arr[25]);
                    tw.setRetweetCount(arr[26]);
                    tw.setHashtags(arr[27]);
                    tw.setUrls(arr[28]);
                    tw.setUserMentions(arr[29]);
                    tw.setPollChoices(arr[30]);

                    twMapper.insertSelective(tw);

                    //idCount++;
                    oldString="";
                }
            }

            System.out.println("csv表格中所有行数：" + allString.size());

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

}
