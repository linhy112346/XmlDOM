/*
package com.boyue.linhy.xmlparser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.boyue.linhy.testsql.R;
import com.boyue.linhy.xmlparser.Utils.LogUtils;
import com.boyue.linhy.xmlparser.beans.CategroyEntryBean;
import com.boyue.linhy.xmlparser.beans.CategroySubEntryBean;
import com.boyue.linhy.xmlparser.beans.Ec.EntryBean;
import com.boyue.linhy.xmlparser.beans.ExSeSubBean;
import com.boyue.linhy.xmlparser.beans.ExampleCategroyBean;
import com.boyue.linhy.xmlparser.beans.ExampleSenseEntryBean;
import com.boyue.linhy.xmlparser.beans.SenseCategroySubEntryBean;
import com.boyue.linhy.xmlparser.beans.SenseCategroyWordBean;
import com.boyue.linhy.xmlparser.beans.SenseEntryBean1;
import com.boyue.linhy.xmlparser.beans.SenseSubEntryBean;
import com.boyue.linhy.xmlparser.beans.SubEntryBean;
import com.boyue.linhy.xmlparser.db.WordDB.WordBean;
import com.boyue.linhy.xmlparser.db.WordDB.WordDAO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity
        extends AppCompatActivity
{
    TextView text;

    EntryBean             mEntry;
    SenseEntryBean1       mSeEntry;
    ExampleSenseEntryBean mExSeEntry;
    CategroyEntryBean     mCaEntry;
    SenseCategroyWordBean mSeCaEntry;
    ExampleCategroyBean   mExSeCaEntry;

    SubEntryBean              mSub;
    SenseSubEntryBean         mSeSub;
    ExSeSubBean               mExSeSub;
    CategroySubEntryBean      mCaSub;
    SenseCategroySubEntryBean mSeCaSub;

    String mSubdict = "null";

    Thread mThread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEntry = new EntryBean();
        mSeEntry = new SenseEntryBean1();
        mExSeEntry = new ExampleSenseEntryBean();
        mCaEntry = new CategroyEntryBean();
        mSeCaEntry = new SenseCategroyWordBean();
        mExSeCaEntry = new ExampleCategroyBean();

        mSub = new SubEntryBean();
        mSeSub = new SenseSubEntryBean();
        mExSeSub = new ExSeSubBean();
        mCaSub = new CategroySubEntryBean();
        mSeCaSub = new SenseCategroySubEntryBean();


        text = (TextView) findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeList();
            }
        });
    }

    private void changeList() {
        if (mThread == null) {
            mThread = new Thread(new Runnable() {
                @Override
                public void run() {


                    try {
                        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder        builder;
                        builder = builderFactory.newDocumentBuilder();
                        Document document = builder.parse(getAssets().open("ce.xml"));
                        Element  element  = document.getDocumentElement();
                        NodeList nodeList = element.getChildNodes();
                        //获取1级目录子节点名字和个数
                        for (int x = 0; x < nodeList.getLength(); x++) {

                            if (nodeList.item(x) instanceof Element) {
                                Log.d("----------", "---------");
                                Element element1 = (Element) element.getChildNodes()
                                                                    .item(x);
                                NodeList ListFirst = element1.getChildNodes();
                                Log.d("1级目录名",
                                      element1.getTagName() + " " + element1.getAttribute("char"));
                                mSubdict = element1.getAttribute("char");
                                for (int a = 0; a < ListFirst.getLength(); a++) {
                                    if (ListFirst.item(a) instanceof Element) {
                                        Element element2 = (Element) element1.getChildNodes()
                                                                             .item(a);
                                        Log.e("----->>",element2.getTagName());
                                        NodeList ListSecond = element2.getChildNodes();
                                        Log.d("2级目录名", "----" + element2.getTagName());

                                        for (int b = 0; b < ListSecond.getLength(); b++) {
                                            if (ListSecond.item(b) instanceof Element) {
                                                Element  element3  = (Element) ListSecond.item(b);
                                                NodeList ListThird = element3.getChildNodes();
                                                if (element3.getChildNodes()
                                                            .getLength() == 1 || element3.getTagName()
                                                                                         .equals("ex"))
                                                {
                                                    if (element3.getTagName()
                                                                .equals("ex"))
                                                    {
                                                        Log.d("3级目录名",
                                                              "    ----" + element3.getTagName() + "(" + clearNotChinese(
                                                                      element3.getTextContent()) + ")");
                                                    } else {
                                                        Log.d("3级目录名",
                                                              "    ----" + element3.getTagName() + "(" + element3.getTextContent() + ")");
                                                    }
                                                    if (element3.getParentNode()
                                                                .getNodeName()
                                                                .equals("entry"))
                                                    {
                                                        mEntry.set(element3.getTagName(),
                                                                   element3.getTextContent());
                                                    }

                                                } else if (element3.getChildNodes()
                                                                   .getLength() > 1)
                                                {
                                                    Log.d("3级目录名",
                                                          "    ----" + element3.getTagName());
                                                    for (int c = 0; c < ListThird.getLength(); c++)
                                                    {
                                                        if (ListThird.item(c) instanceof Element) {
                                                            Element element4 = (Element) ListThird.item(
                                                                    c);


                                                            if (element4.getChildNodes()
                                                                        .getLength() == 1 || element4.getTagName()
                                                                                                     .equals("ex"))
                                                            {
                                                                if (element4.getTagName()
                                                                            .equals("ex"))
                                                                {
                                                                    Log.d("4级目录名",
                                                                          "        ----" + element4.getTagName() + "(" + clearNotChinese(
                                                                                  element4.getTextContent()) + ")");
                                                                } else {
                                                                    Log.d("4级目录名",
                                                                          "        ----" + element4.getTagName() + "(" + element4.getTextContent() + ")");
                                                                }
                                                                if (element2.getTagName()
                                                                            .equals("entry"))
                                                                {
                                                                    if (element3.getTagName()
                                                                                .equals("sense"))
                                                                    {
                                                                        mSeEntry.set(element4.getTagName(),
                                                                                     element4.getTextContent());
                                                                    } else if (element3.getTagName()
                                                                                       .equals("categroy"))
                                                                    {
                                                                        mCaEntry.set(element4.getTagName(),
                                                                                     element4.getTextContent());
                                                                    } else if (element3.getTagName()
                                                                                       .equals("sub-entry"))
                                                                    {
                                                                        mSub.set(element4.getTagName(),
                                                                                 element4.getTextContent());
                                                                    }
                                                                }
                                                            } else if (element4.getChildNodes()
                                                                               .getLength() > 1)
                                                            {
                                                                Log.d("4级目录名",
                                                                      "        ----" + element4.getTagName());

                                                                NodeList ListFourth = element4.getChildNodes();


                                                                for (int d = 0; d < ListFourth.getLength(); d++)
                                                                {
                                                                    if (ListFourth.item(d) instanceof Element) {
                                                                        Element element5 = (Element) ListFourth.item(
                                                                                d);

                                                                        if (element5.getChildNodes()
                                                                                    .getLength() == 1 || element5.getTagName()
                                                                                                                 .equals("ex"))
                                                                        {
                                                                            if (element5.getTagName()
                                                                                        .equals("ex"))
                                                                            {
                                                                                Log.d("5级目录名",
                                                                                      "            ----" + element5.getTagName() + "(" + clearNotChinese(
                                                                                              element5.getTextContent()) + ")");
                                                                            } else {
                                                                                Log.d("5级目录名",
                                                                                      "            ----" + element5.getTagName() + "(" + element5.getTextContent() + ")");
                                                                            }
                                                                            if (element4.getTagName()
                                                                                        .equals("example") && element3.getTagName()
                                                                                                                      .equals("sense"))
                                                                            {
                                                                                if (element5.getTagName()
                                                                                            .equals("ex"))
                                                                                {
                                                                                    mExSeEntry.set(
                                                                                            element5.getTagName(),
                                                                                            element5.getTextContent()
                                                                                                    .replace(
                                                                                                            "\n",
                                                                                                            "")
                                                                                                    .replace(
                                                                                                            " ",
                                                                                                            ""));
                                                                                } else {
                                                                                    mExSeEntry.set(
                                                                                            element5.getTagName(),
                                                                                            element5.getTextContent()
                                                                                                    .replace(
                                                                                                            "\n",
                                                                                                            ""));
                                                                                }
                                                                            } else if (element4.getTagName()
                                                                                               .equals("sense") && element3.getTagName()
                                                                                                                           .equals("categroy"))
                                                                            {
                                                                                mSeCaEntry.set(
                                                                                        element5.getTagName(),
                                                                                        element5.getTextContent());
                                                                            } else if (element4.getTagName()
                                                                                               .equals("sense") && element3.getTagName()
                                                                                                                           .equals("sub-entry"))
                                                                            {
                                                                                mSeSub.set(element5.getTagName(),
                                                                                           element5.getTextContent());
                                                                            } else if (element4.getTagName()
                                                                                               .equals("categroy") && element3.getTagName()
                                                                                                                              .equals("sub-entry"))
                                                                            {
                                                                                mCaSub.set(element5.getTagName(),
                                                                                           element5.getTextContent());
                                                                            }
                                                                        } else if (element5.getChildNodes()
                                                                                           .getLength() > 1)
                                                                        {
                                                                            Log.d("5级目录名",
                                                                                  "            ----" + element5.getTagName());
                                                                            NodeList ListFiv = element5.getChildNodes();
                                                                            for (int e = 0; e < ListFiv.getLength(); e++)
                                                                            {
                                                                                if (ListFiv.item(e) instanceof Element) {
                                                                                    Element element6 = (Element) ListFiv.item(
                                                                                            e);
                                                                                    if (element6.getChildNodes()
                                                                                                .getLength() == 1 || element6.getTagName()
                                                                                                                             .equals("ex"))
                                                                                    {
                                                                                        if (element6.getTagName()
                                                                                                    .equals("ex"))
                                                                                        {
                                                                                            Log.d("6级目录名",
                                                                                                  "                ----" + element6.getTagName() + "(" + clearNotChinese(
                                                                                                          element6.getTextContent()) + ")");
                                                                                        } else {
                                                                                            Log.d("6级目录名",
                                                                                                  "                ----" + element6.getTagName() + "(" + element6.getTextContent() + ")");
                                                                                        }
                                                                                        if (element5.getTagName()
                                                                                                    .equals("sense") && element4.getTagName()
                                                                                                                                .equals("categroy") && element3.getTagName()
                                                                                                                                                               .equals("sub-entry"))
                                                                                        {
                                                                                            mSeCaSub.set(
                                                                                                    element6.getTagName(),
                                                                                                    element6.getTextContent());
                                                                                        } else if (element5.getTagName()
                                                                                                           .equals("example") && element4.getTagName()
                                                                                                                                         .equals("sense") && element3.getTagName()
                                                                                                                                                                     .equals("categroy"))
                                                                                        {
                                                                                            if (element6.getTagName()
                                                                                                        .equals("ex"))
                                                                                            {
                                                                                                mExSeCaEntry.set(
                                                                                                        element6.getTagName(),
                                                                                                        element6.getTextContent()
                                                                                                                .replace(
                                                                                                                        "\n",
                                                                                                                        "")
                                                                                                                .replace(
                                                                                                                        " ",
                                                                                                                        ""));
                                                                                            } else {
                                                                                                mExSeCaEntry.set(
                                                                                                        element6.getTagName(),
                                                                                                        element6.getTextContent()
                                                                                                                .replace(
                                                                                                                        "\n",
                                                                                                                        ""));
                                                                                            }
                                                                                        } else if (element5.getTagName()
                                                                                                           .equals("example") && element4.getTagName()
                                                                                                                                         .equals("sense") && element3.getTagName()
                                                                                                                                                                     .equals("sub-entry"))
                                                                                        {
                                                                                            if (element6.getTagName()
                                                                                                        .equals("ex"))
                                                                                            {
                                                                                                mExSeSub.set(
                                                                                                        element6.getTagName(),
                                                                                                        element6.getTextContent()
                                                                                                                .replace(
                                                                                                                        "\n",
                                                                                                                        "")
                                                                                                                .replace(
                                                                                                                        " ",
                                                                                                                        ""));
                                                                                            } else {
                                                                                                mExSeSub.set(
                                                                                                        element6.getTagName(),
                                                                                                        element6.getTextContent()
                                                                                                                .replace(
                                                                                                                        "\n",
                                                                                                                        ""));
                                                                                            }
                                                                                        }
                                                                                    } else if (element6.getChildNodes()
                                                                                                       .getLength() > 1)
                                                                                    {
                                                                                        Log.d("6级目录名",
                                                                                              "                ----" + element6.getTagName());
                                                                                        NodeList ListSix = element6.getChildNodes();
                                                                                        for (int f = 0; f < ListSix.getLength(); f++)
                                                                                        {
                                                                                            if (ListSix.item(
                                                                                                    f) instanceof Element)
                                                                                            {
                                                                                                Element element7 = (Element) ListSix.item(
                                                                                                        f);

                                                                                                if (element7.getChildNodes()
                                                                                                            .getLength() == 1 || element7.getTagName()
                                                                                                                                         .equals("ex"))
                                                                                                {
                                                                                                    if (element7.getTagName()
                                                                                                                .equals("ex"))
                                                                                                    {
                                                                                                        Log.d("7级目录名",
                                                                                                              "                    ----" + element7.getTagName() + "(" + clearNotChinese(
                                                                                                                      element7.getTextContent()) + ")");
                                                                                                    } else {
                                                                                                        Log.d("7级目录名",
                                                                                                              "                    ----" + element7.getTagName() + "(" + element7.getTextContent() + ")");
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        if (element5.getTagName()
                                                                                    .equals("sense") && element4.getTagName()
                                                                                                                .equals("categroy") && element3.getTagName()
                                                                                                                                               .equals("sub-entry"))
                                                                        {
                                                                            LogUtils.Log("---------",
                                                                                         "senseCateSub");
                                                                            LogUtils.Log(
                                                                                    "senseCateSub style",
                                                                                    mSeCaSub.getStyle());
                                                                            LogUtils.Log(
                                                                                    "senseCateSub field",
                                                                                    mSeCaSub.getField());
                                                                            LogUtils.Log(
                                                                                    "senseCateSub def",
                                                                                    mSeCaSub.getDef());
                                                                            LogUtils.Log(
                                                                                    "senseCateSub tran",
                                                                                    mSeCaSub.getTran());
                                                                            mSeCaSub.resetSense();
                                                                        } else if (element5.getTagName()
                                                                                           .equals("example") && element4.getTagName()
                                                                                                                         .equals("sense") && element3.getTagName()
                                                                                                                                                     .equals("categroy"))
                                                                        {
                                                                            LogUtils.Log("---------",
                                                                                         "mExSeCaEntry");
                                                                            LogUtils.Log(
                                                                                    "mExSeCaEntry ex",
                                                                                    mExSeCaEntry.getEx());
                                                                            LogUtils.Log(
                                                                                    "mExSeCaEntry pinyin",
                                                                                    mExSeCaEntry.getPinyin());
                                                                            LogUtils.Log(
                                                                                    "mExSeCaEntry tran",
                                                                                    mExSeCaEntry.getTran());
                                                                            mExSeCaEntry.resetExample();
                                                                        }
                                                                        if (element5.getTagName()
                                                                                    .equals("example") && element4.getTagName()
                                                                                                                  .equals("sense") && element3.getTagName()
                                                                                                                                              .equals("sub-entry"))
                                                                        {
                                                                            LogUtils.Log("---------",
                                                                                         "mExSeSub");
                                                                            LogUtils.Log(
                                                                                    "mExSeSub ex",
                                                                                    mExSeSub.getEx());
                                                                            LogUtils.Log(
                                                                                    "mExSeSub pinyin",
                                                                                    mExSeSub.getPinyin());
                                                                            LogUtils.Log(
                                                                                    "mExSeSub tran",
                                                                                    mExSeSub.getTran());
                                                                            mExSeSub.resetExample();
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            if (element4.getTagName()
                                                                        .equals("example") && element3.getTagName()
                                                                                                      .equals("sense"))
                                                            {
                                                                LogUtils.Log("---------",
                                                                             "example");
                                                                LogUtils.Log("example ex",
                                                                             mExSeEntry.getEx());
                                                                LogUtils.Log("example pinyin",
                                                                             mExSeEntry.getPinyin());
                                                                LogUtils.Log("example tran",
                                                                             mExSeEntry.getTran());
                                                                mExSeEntry.resetExample();
                                                            } else if (element4.getTagName()
                                                                               .equals("sense") && element3.getTagName()
                                                                                                           .equals("categroy"))
                                                            {
                                                                LogUtils.Log("---------",
                                                                             "senseCategroyWord");
                                                                LogUtils.Log(
                                                                        "senseCategroyWord style",
                                                                        mSeCaEntry.getStyle());
                                                                LogUtils.Log(
                                                                        "senseCategroyWord field",
                                                                        mSeCaEntry.getField());
                                                                LogUtils.Log("senseCategroyWord def",
                                                                             mSeCaEntry.getDef());
                                                                LogUtils.Log(
                                                                        "senseCategroyWord tran",
                                                                        mSeCaEntry.getTran());
                                                                mSeCaEntry.resetSense();

                                                            } else if (element4.getTagName()
                                                                               .equals("sense") && element3.getTagName()
                                                                                                           .equals("sub-entry"))
                                                            {
                                                                LogUtils.Log("---------", "mSeSub");
                                                                LogUtils.Log("mSeSub style",
                                                                             mSeSub.getStyle());
                                                                LogUtils.Log("mSeSub field",
                                                                             mSeSub.getField());
                                                                LogUtils.Log("mSeSub def",
                                                                             mSeSub.getDef());
                                                                LogUtils.Log("mSeSub tran",
                                                                             mSeSub.getTran());
                                                                mSeSub.resetSense();

                                                            } else if (element4.getTagName()
                                                                               .equals("categroy") && element3.getTagName()
                                                                                                              .equals("sub-entry"))
                                                            {
                                                                LogUtils.Log("---------",
                                                                             "categroySubEntry");
                                                                LogUtils.Log("mCaSub pos",
                                                                             mCaSub.getPos());
                                                                mCaSub.reset();

                                                            }
                                                        }
                                                    }
                                                }
                                                if (element2.getTagName()
                                                            .equals("entry"))
                                                {
                                                    if (element3.getTagName()
                                                                .equals("sense"))
                                                    {
                                                        LogUtils.Log("---------", "sense");
                                                        LogUtils.Log("sense style",
                                                                     mSeEntry.getStyle());
                                                        LogUtils.Log("sense field",
                                                                     mSeEntry.getField());
                                                        LogUtils.Log("sense def",
                                                                     mSeEntry.getDef());
                                                        LogUtils.Log("sense tran",
                                                                     mSeEntry.getTran());
                                                        mSeEntry.resetSense();
                                                    } else if (element3.getTagName()
                                                                       .equals("categroy"))
                                                    {
                                                        LogUtils.Log("---------", "categroy");
                                                        LogUtils.Log("categroy pos",
                                                                     mCaEntry.getPos());
                                                        mCaEntry.reset();
                                                    } else if (element3.getTagName()
                                                                       .equals("sub-entry"))
                                                    {
                                                        LogUtils.Log("---------", "sub-entry");
                                                        LogUtils.Log("sub-entry word",
                                                                     mSub.getWord());
                                                        LogUtils.Log("sub-entry pinyin",
                                                                     mSub.getPinyin());
                                                        LogUtils.Log("sub-entry pos",
                                                                     mSub.getPos());
                                                        mSub.resetEntry();
                                                    }
                                                }

                                            }
                                        }
                                        if (element2.getTagName()
                                                    .equals("entry") && element1.getTagName()
                                                                                .equals("subdict"))
                                        {
                                            mEntry.setSubdict(mSubdict);
                                            LogUtils.Log("---------", "entry");
                                            LogUtils.Log("entry word", mEntry.getWord());
                                            LogUtils.Log("entry pinyin", mEntry.getPinyin());
                                            LogUtils.Log("entry pos", mEntry.getPos());
                                            LogUtils.Log("entry see", mEntry.getSee());
                                            LogUtils.Log("entry subdict", mEntry.getSubdict());
                                            addToDB_word(mEntry.getWord(),
                                                         mEntry.getPinyin(),
                                                         mEntry.getPos(),
                                                         mEntry.getSee(),
                                                         mEntry.getSubdict());
                                            mEntry.resetEntry();
                                        }
                                    }
                                }
                            }
                        }

                    } catch (ParserConfigurationException e)

                    {
                        e.printStackTrace();
                    } catch (SAXException e)

                    {
                        e.printStackTrace();
                    } catch (IOException e)

                    {
                        e.printStackTrace();
                    }
                    Log.e("完成了！", "finish");
                    mThread.interrupt();
                    mThread = null;
                }
            });
            mThread.start();
        }
    }


    private boolean isElementExist(Element entry, String name) {
        return (entry.getElementsByTagName(name)
                     .getLength() > 0);
    }

    private void addToDB_word(String word, String pinyin, String pos, String see, String subdict)
    {

        WordBean.WordAddShelf wordAddShelf = new WordBean.WordAddShelf();
        wordAddShelf.setWord(word);
        wordAddShelf.setPinyin(pinyin);
        wordAddShelf.setPos(pos);
        wordAddShelf.setSee(see);
        wordAddShelf.setSubdict(subdict);
        new WordDAO(MainActivity.this).insert(wordAddShelf);

    }

    //去除所有非汉字的方法。
    private static String clearNotChinese(String buff) {
        String tmpString = buff.replaceAll("[^\u4E00-\u9FA5]", "");//去掉所有中英文符号
        char[] carr      = tmpString.toCharArray();
        for (int i = 0; i < tmpString.length(); i++) {
            if (carr[i] < 0xFF) {
                carr[i] = ' ';//过滤掉非汉字内容
            }
        }
        return String.copyValueOf(carr)
                     .trim();
    }




















 else if(element7.getChildNodes()
            .

    getLength() >1)

    {
        Log.d("7级目录名123", "                    ----" + element7.getTagName());
        NodeList ListSeven = element7.getChildNodes();
        for (int g = 0; g < ListSeven.getLength(); g++) {
            if (ListSeven.item(g) instanceof Element) {
                Element element8 = (Element) ListSeven.item(f);
                Log.d("8级目录名",
                      "                        ----" + element8.getTagName() + "(" + element8.getTextContent() + ")");

                if (element8.getChildNodes()
                            .getLength() > 1)
                {
                    NodeList ListEight = element8.getChildNodes();
                    for (int h = 0; h < ListEight.getLength(); h++) {
                        if (ListEight.item(g) instanceof Element) {
                            Element element9 = (Element) ListEight.item(f);
                            Log.d("9级目录名",
                                  "                            ----" + element9.getTagName() + "(" + element9.getChildNodes()
                                                                                                             .getLength() + ")");

                        }
                    }
                }
            }
        }
    }


}
*/
