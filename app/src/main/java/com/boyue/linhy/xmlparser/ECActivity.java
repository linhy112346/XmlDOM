package com.boyue.linhy.xmlparser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.boyue.linhy.testsql.R;
import com.boyue.linhy.xmlparser.beans.Ec.EntryBean;
import com.boyue.linhy.xmlparser.beans.Ec.IdiomBean;
import com.boyue.linhy.xmlparser.beans.Ec.PhraseBean;
import com.boyue.linhy.xmlparser.beans.Ec.RunonBean;
import com.boyue.linhy.xmlparser.beans.Ec.SenseEntryBean;
import com.boyue.linhy.xmlparser.beans.Ec.SenseIdiomBean;
import com.boyue.linhy.xmlparser.beans.Ec.SensePhraseBean;
import com.boyue.linhy.xmlparser.beans.Ec.SenseRunonBean;
import com.boyue.linhy.xmlparser.db.Ec.EntryDAO;
import com.boyue.linhy.xmlparser.db.Ec.IdiomDAO;
import com.boyue.linhy.xmlparser.db.Ec.PhraseDAO;
import com.boyue.linhy.xmlparser.db.Ec.RunonDAO;
import com.boyue.linhy.xmlparser.db.Ec.SenseEntryDAO;
import com.boyue.linhy.xmlparser.db.Ec.SenseIdiomDAO;
import com.boyue.linhy.xmlparser.db.Ec.SensePhraseDAO;
import com.boyue.linhy.xmlparser.db.Ec.SenseRunonDAO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ECActivity
        extends AppCompatActivity
{
    private TextView text;

    private Thread mThread = null;

    private EntryBean       mEntryBean;
    private SenseEntryBean  mSenseEntryBean;
    private IdiomBean       mIdiomBean;
    private SenseIdiomBean  mSenseIdiomBean;
    private RunonBean       mRunonBean;
    private SenseRunonBean  mSenseRunonBean;
    private PhraseBean      mPhraseBean;
    private SensePhraseBean mSensePhraseBean;

    private int mEntryId  = 1;
    private int mIdiomId  = 1;
    private int mRunonId  = 1;
    private int mPhraseId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ec);

        mEntryBean = new EntryBean();
        mSenseEntryBean = new SenseEntryBean();
        mIdiomBean = new IdiomBean();
        mSenseIdiomBean = new SenseIdiomBean();
        mRunonBean = new RunonBean();
        mSenseRunonBean = new SenseRunonBean();
        mPhraseBean = new PhraseBean();
        mSensePhraseBean = new SensePhraseBean();

        text = (TextView) findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Click!", "!!!");
                if (mThread == null) {
                    mThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                                DocumentBuilder        builder;
                                builder = builderFactory.newDocumentBuilder();
                                Document document = builder.parse(getAssets().open("ec.xml"));
                                Element  element  = document.getDocumentElement();
                                NodeList nodeList = element.getChildNodes();
                                parser(nodeList);
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
        });
    }

    private String mSubdict = "null";

    private void parser(NodeList nodeList) {
        for (int index = 0; index < nodeList.getLength(); index++) {
            if (nodeList.item(index) instanceof Element) {
                Element element = (Element) (nodeList.item(index));
                if (element.getTagName()
                           .equals("subdict"))
                {
                    setSubdict(element.getAttribute("char"));
                    mEntryBean.setSubdict(mSubdict);
                }
                if (element.hasChildNodes()) {
                    parser(element.getChildNodes());
                }
                if (element.getParentNode()
                           .getNodeName()
                           .equals("subdict"))
                {
                    Log.e("--", element.getTagName());
                    addToDB_Entry(mEntryBean);
                    mEntryBean.resetEntry();
                } else if (element.getParentNode()
                                  .getNodeName()
                                  .equals("entry"))
                {
                    if (element.getChildNodes()
                               .getLength() != 1)
                    {
                        Log.e("----->", element.getTagName());
                        if (element.getTagName()
                                   .equals("sense"))
                        {
                            addToDB_SenseEntry(mSenseEntryBean);
                            mSenseEntryBean.resetSense();
                        } else if (element.getTagName()
                                          .equals("idiom"))
                        {
                            addToDB_Idiom(mIdiomBean);
                            mIdiomBean.resetIdiom();
                        } else if (element.getTagName()
                                          .equals("runon"))
                        {
                            addToDB_Runon(mRunonBean);
                            mRunonBean.resetRunon();
                        } else if (element.getTagName()
                                          .equals("phrase"))
                        {
                            addToDB_Phrase(mPhraseBean);
                            mPhraseBean.resetPhrase();
                        }

                    } else {
                        setEntry(element);
                    }
                } else if (element.getParentNode()
                                  .getNodeName()
                                  .equals("sense") && element.getParentNode()
                                                             .getParentNode()
                                                             .getNodeName()
                                                             .equals("entry"))
                {
                    if (element.getChildNodes()
                               .getLength() != 1)
                    {
                        Log.e("----------", element.getTagName());
                    } else {
                        mSenseEntryBean.setEntryId(mEntryId);
                        setEntrySense(element);
                    }
                } else if (element.getParentNode()
                                  .getNodeName()
                                  .equals("idiom"))
                {
                    if (element.getChildNodes()
                               .getLength() != 1)
                    {
                        Log.e("-------->>", element.getTagName());
                    } else {
                        mIdiomBean.setEntryId(mEntryId);
                        setIdiom(element);
                    }

                    if (element.getTagName()
                               .equals("sense"))
                    {
                        addToDB_SenseIdiom(mSenseIdiomBean);
                        mSenseIdiomBean.resetSense();
                    }
                } else if (element.getParentNode()
                                  .getNodeName()
                                  .equals("sense") && element.getParentNode()
                                                             .getParentNode()
                                                             .getNodeName()
                                                             .equals("idiom"))
                {
                    if (element.getChildNodes()
                               .getLength() != 1)
                    {
                        Log.e("--------------", element.getTagName());
                    } else {
                        mSenseIdiomBean.setIdiomId(mIdiomId);
                        setIdiomSense(element);
                    }
                } else if (element.getParentNode()
                                  .getNodeName()
                                  .equals("runon") && element.getParentNode()
                                                             .getParentNode()
                                                             .getNodeName()
                                                             .equals("entry"))
                {
                    if (element.getChildNodes()
                               .getLength() != 1)
                    {
                        Log.e("----------", element.getTagName());
                    } else {
                        mRunonBean.setEntryId(mEntryId);
                        setRunon(element);
                    }

                    if (element.getTagName()
                               .equals("sense"))
                    {
                        addToDB_SenseRunon(mSenseRunonBean);
                        mSenseRunonBean.resetSense();
                    }

                } else if (element.getParentNode()
                                  .getNodeName()
                                  .equals("sense") && element.getParentNode()
                                                             .getParentNode()
                                                             .getNodeName()
                                                             .equals("runon"))
                {
                    if (element.getChildNodes()
                               .getLength() != 1)
                    {
                        Log.e("--------------", element.getTagName());
                    } else {
                        mSenseRunonBean.setRunonId(mRunonId);
                        setRunonSense(element);
                    }
                } else if (element.getParentNode()
                                  .getNodeName()
                                  .equals("phrase") && element.getParentNode()
                                                              .getParentNode()
                                                              .getNodeName()
                                                              .equals("entry"))
                {
                    if (element.getChildNodes()
                               .getLength() != 1)
                    {
                        Log.e("----------", element.getTagName());
                    } else {
                        mPhraseBean.setEntryId(mEntryId);
                        setPhrase(element);
                    }

                    if (element.getTagName()
                               .equals("sense"))
                    {
                        addToDB_SensePhrase(mSensePhraseBean);
                        mSensePhraseBean.resetSense();
                    }

                } else if (element.getParentNode()
                                  .getNodeName()
                                  .equals("sense") && element.getParentNode()
                                                             .getParentNode()
                                                             .getNodeName()
                                                             .equals("phrase"))
                {
                    if (element.getChildNodes()
                               .getLength() != 1)
                    {
                        Log.e("--------------", element.getTagName());
                    } else {
                        mSensePhraseBean.setPhraseId(mPhraseId);
                        setPhraseSense(element);
                    }
                }
            }
        }
    }

    private void setSubdict(String subdict) {
        mSubdict = subdict;
        Log.e("subdict", subdict);
    }

    private void setEntry(Element element) {
        Log.e("------: " + element.getTagName(), element.getTextContent());
        mEntryBean.set(element.getTagName(), element.getTextContent());
    }

    private void setEntrySense(Element element) {
        Log.e("----------: " + element.getTagName(), element.getTextContent());
        mSenseEntryBean.set(element.getTagName(), element.getTextContent());
    }

    private void setIdiom(Element element) {
        Log.e("----------: " + element.getTagName(), element.getTextContent());
        mIdiomBean.set(element.getTagName(), element.getTextContent());
    }

    private void setIdiomSense(Element element) {
        Log.e("--------------: " + element.getTagName(), element.getTextContent());
        mSenseIdiomBean.set(element.getTagName(), element.getTextContent());
    }

    private void setRunon(Element element) {
        Log.e("----------: " + element.getTagName(), element.getTextContent());
        mRunonBean.set(element.getTagName(), element.getTextContent());
    }

    private void setRunonSense(Element element) {
        Log.e("--------------: " + element.getTagName(), element.getTextContent());
        mSenseRunonBean.set(element.getTagName(), element.getTextContent());
    }

    private void setPhrase(Element element) {
        Log.e("----------: " + element.getTagName(), element.getTextContent());
        mPhraseBean.set(element.getTagName(), element.getTextContent());
    }

    private void setPhraseSense(Element element) {
        Log.e("--------------: " + element.getTagName(), element.getTextContent());
        mSensePhraseBean.set(element.getTagName(), element.getTextContent());
    }

    /**
     *
     * 保存数据库
     *
     */

    private void addToDB_Entry(EntryBean entryBean)
    {
        new EntryDAO(ECActivity.this).insert(entryBean);
        mEntryId = mEntryId + 1;
    }

    private void addToDB_SenseEntry(SenseEntryBean senseEntryBean)
    {
        new SenseEntryDAO(ECActivity.this).insert(senseEntryBean);
    }

    private void addToDB_Idiom(IdiomBean idiomBean)
    {
        new IdiomDAO(ECActivity.this).insert(idiomBean);
        mIdiomId = mIdiomId + 1;
    }

    private void addToDB_SenseIdiom(SenseIdiomBean senseIdiomBean)
    {
        new SenseIdiomDAO(ECActivity.this).insert(senseIdiomBean);
    }

    private void addToDB_Runon(RunonBean runonBean)
    {
        new RunonDAO(ECActivity.this).insert(runonBean);
        mRunonId = mRunonId + 1;
    }

    private void addToDB_SenseRunon(SenseRunonBean senseRunonBean)
    {
        new SenseRunonDAO(ECActivity.this).insert(senseRunonBean);
    }

    private void addToDB_Phrase(PhraseBean phraseBean)
    {
        new PhraseDAO(ECActivity.this).insert(phraseBean);
        mPhraseId = mPhraseId + 1;
    }

    private void addToDB_SensePhrase(SensePhraseBean sensePhraseBean)
    {
        new SensePhraseDAO(ECActivity.this).insert(sensePhraseBean);
    }
}