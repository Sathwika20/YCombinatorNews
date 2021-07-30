package com.bridgelabz.page;

import com.bridgelabz.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class NewsHeadings extends Base {

    private Integer maxHeadingsToProcess = 120;

    @FindBy(xpath = "//a[@class = 'storylink']")
    private List<WebElement> titleList;

    @FindBy(xpath = "//a[text() = 'More']")
    private WebElement more;

    public NewsHeadings(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }


    public PopularHeading findPopularHeading() {
        int totalNoOfHeadings = 0;
        HashMap<Integer, List<String>> headingMap =  new HashMap<>();
        HashMap<String,Integer> wordMap = new HashMap<>();
        while(totalNoOfHeadings < maxHeadingsToProcess){
            totalNoOfHeadings = totalNoOfHeadings+ titleList.size();
            for (WebElement titleElement: titleList){
                processHeading(titleElement,headingMap);
                processWordsInHeading(titleElement.getText(), wordMap);
            }
            more.click();
        }
        String popularWord = findPopularWord(wordMap);
        System.out.println("Popular word is:" + popularWord);

        PopularHeading popularHeading = findPopularHeading(headingMap, popularWord);
        return popularHeading;
    }

    /**This method is designed to take the String as heading and split the String using regex,
     * calculate the occurance of the word and store it in a hashmap
     *
     * @param heading
     * @param wordMap
     */
    private void processWordsInHeading(String heading, HashMap<String,Integer> wordMap){
       String[] words =  heading.split(" ");
        for (String word: words){
            if(wordMap.containsKey(word)){
                Integer count = wordMap.get(word);
                wordMap.put(word,count+1);
            }else {
                wordMap.put(word,1);
            }
        }
    }

    /**This method is designed to take the webElement as input, using webelement it extracts the title String
     * and points integer and stores it in hashmap
     * To extract the points it uses the xpath relative to the given title webelement and finds the points element and
     * extracts the points text which contains extra charecters then it splits the point text to extract the point integer
     *
     * @param headingElement
     * @param headingMap
     */
    private void processHeading(WebElement headingElement, HashMap<Integer, List<String>> headingMap){
        String titleText = headingElement.getText();
        WebElement pointsElement = headingElement.findElement(
                By.xpath("//parent::td//parent::tr//following::tr//span[@class='score']"));
        String pointsText = pointsElement.getText();
        //splitting the word using regex
        String[] words = pointsText.split(" ");
        Integer pointValue = Integer.parseInt(words[0]);
        List headingList = null;
        if(!headingMap.containsKey(pointValue)){
            headingList = new ArrayList<String>();
        }else{
            headingList =  headingMap.get(pointValue);
        }
        headingList.add(titleText);
        headingMap.put(pointValue,headingList);
    }

    /**
     * This method is designed to iterate words through the wordMap to get the word which has the maximum count
     * @param wordMap
     * @return popular word as String
     */
    private String findPopularWord(HashMap<String,Integer> wordMap) {
        int maxCount = 0;
        String maxCountKey = null;
        //other type of for loop
        for(Map.Entry<String, Integer> wordEntry: wordMap.entrySet()){
            String key = wordEntry.getKey();
            int value = wordEntry.getValue();
            if(value > maxCount){
                maxCount = value;
                maxCountKey = key;
            }
        }
        return maxCountKey;
    }

    /**
     * This method is designed to process the list of headings and find most popular heading using most popular word which is passed as input parameter
     * @param headingMap
     * @param popularWord
     * @return the heading with points as PopularHeading object
     */
    private PopularHeading findPopularHeading(HashMap<Integer,List<String>> headingMap, String popularWord){
        PopularHeading popularHeading = null;
        for (Map.Entry<Integer,List<String>> headingEntry: headingMap.entrySet()){
            List<String> headingsList = headingEntry.getValue();
            for(String heading: headingsList){
                List headingWordList = Arrays.asList(heading.split(" "));
                if(headingWordList.contains(popularWord)){
                    popularHeading = new PopularHeading(heading, headingEntry.getKey());
                    return popularHeading;
                }
            }
        }
        return null;
    }
}

