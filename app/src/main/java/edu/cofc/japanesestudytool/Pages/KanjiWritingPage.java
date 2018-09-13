package edu.cofc.japanesestudytool.Pages;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.cofc.japanesestudytool.DrawingView;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Term;
import edu.cofc.japanesestudytool.TermMenuMetrics;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.app.Dialog;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.util.ArrayList;

public class KanjiWritingPage extends AppCompatActivity implements View.OnClickListener
{
    private DrawingView drawView;
    private float smallBrush;
    private float mediumBrush;
    private float largeBrush;
    private ImageButton drawBtn, eraseBtn, newBtn;
    private EditText engHintText,jpnsHintText,kanjiText;
    private TextView kanjiCounter;
    private Button prevButton,nextButton,showEngHintButton, showJpnsHintButton, showKanjiButton;
    private ArrayList<Term> termList;
    private int currentCardNumber=0;
    private boolean useJapaneseHint;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji_writing_page);
        useJapaneseHint =( (TermMenuMetrics) getIntent().getSerializableExtra("metrics")).isJapaneseFirst();
        termList = (ArrayList<Term>)getIntent().getSerializableExtra("termList");

        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush=getResources().getInteger(R.integer.medium_size);
        largeBrush=getResources().getInteger(R.integer.large_size);
        drawView = findViewById(R.id.drawing);
        drawBtn = findViewById(R.id.draw_btn);
        drawBtn.setOnClickListener(this);
        drawView.setBrushSize(mediumBrush);
        eraseBtn = findViewById(R.id.erase_btn);
        eraseBtn.setOnClickListener(this);
        newBtn = findViewById(R.id.new_btn);
        newBtn.setOnClickListener(this);
        drawView.setColor(getResources().getColor(R.color.brushColor,null));

        jpnsHintText= findViewById(R.id.jpnsHintTextBox);
        jpnsHintText.setEnabled(false);
        showJpnsHintButton =findViewById(R.id.showJpnsHintButton);
        showJpnsHintButton.setOnClickListener(this);
        engHintText = findViewById(R.id.engHintTextBox);
        engHintText.setEnabled(false);
        showEngHintButton = findViewById(R.id.showEngHintButton);
        showEngHintButton.setOnClickListener(this);
        if(useJapaneseHint)
        {
            engHintText.setVisibility(View.INVISIBLE);
            showJpnsHintButton.setVisibility(View.INVISIBLE);
        }
        else
        {
            jpnsHintText.setVisibility(View.INVISIBLE);
            showEngHintButton.setVisibility(View.INVISIBLE);
        }
        kanjiText = findViewById(R.id.kanjiCopyTextBox);
        kanjiText.setEnabled(false);
        kanjiText.setVisibility(View.INVISIBLE);
        showKanjiButton = findViewById(R.id.showKanjiButton);
        showKanjiButton.setOnClickListener(this);
        kanjiCounter = findViewById(R.id.kanjiCounter);
        prevButton = findViewById(R.id.prevKanjiButton);
        prevButton.setVisibility(View.INVISIBLE);
        prevButton.setOnClickListener(this);
        nextButton = findViewById(R.id.nextKanjiButton);
        nextButton.setOnClickListener(this);
        loadKanji(termList.get(0));
        if(termList.size()==1)
        {
            nextButton.setVisibility(View.INVISIBLE);
        }
    }

    private void loadKanji(Term term)
    {
        if(useJapaneseHint)
        {
            engHintText.setVisibility(View.INVISIBLE);
            showEngHintButton.setVisibility(View.VISIBLE);
            showJpnsHintButton.setVisibility(View.INVISIBLE);
        }
        else
        {
            jpnsHintText.setVisibility(View.INVISIBLE);
            showJpnsHintButton.setVisibility(View.VISIBLE);
            showEngHintButton.setVisibility(View.INVISIBLE);
        }
        kanjiText.setVisibility(View.INVISIBLE);
        showKanjiButton.setVisibility(View.VISIBLE);
        engHintText.setText(term.getEng());
        jpnsHintText.setText(term.getJpns());
        kanjiText.setText(term.getKanji());

        kanjiCounter.setText((currentCardNumber+1) +"/"+ termList.size());

    }

    @Override
    public void onClick(View view)
    {
        if(view.getId()==R.id.draw_btn)
        {
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Brush size:");
            brushDialog.setContentView(R.layout.brush_chooser);
            Button smallBtn = brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(smallBrush);
                    drawView.setLastBrushSize(smallBrush);
                    drawView.setErase(false);
                    brushDialog.dismiss();
                }
            });
            Button mediumBtn = brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(mediumBrush);
                    drawView.setLastBrushSize(mediumBrush);
                    drawView.setErase(false);
                    brushDialog.dismiss();
                }
            });

            Button largeBtn = brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(largeBrush);
                    drawView.setLastBrushSize(largeBrush);
                    drawView.setErase(false);
                    brushDialog.dismiss();
                }
            });
            brushDialog.show();
        }
        else if(view.getId()==R.id.erase_btn)
        {
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Eraser size:");
            brushDialog.setContentView(R.layout.brush_chooser);
            Button smallBtn = brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(smallBrush);
                    brushDialog.dismiss();
                }
            });
            Button mediumBtn = brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(mediumBrush);
                    brushDialog.dismiss();
                }
            });
            Button largeBtn = brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(largeBrush);
                    brushDialog.dismiss();
                }
            });
            brushDialog.show();
        }
        else if(view.getId()==R.id.new_btn)
        {
            drawView.startNew();
        }
        else if(view.getId()==R.id.prevKanjiButton)
        {
            drawView.startNew();
            if(currentCardNumber == termList.size()-1)
            {
                nextButton.setVisibility(View.VISIBLE);
            }
            if (currentCardNumber == 1)
            {
                prevButton.setVisibility(View.INVISIBLE);
            }
            currentCardNumber--;
            loadKanji(termList.get(currentCardNumber));
        }
        else if(view.getId()==R.id.nextKanjiButton)
        {
            drawView.startNew();
            if(currentCardNumber==0)
            {
                prevButton.setVisibility(View.VISIBLE);
            }
            if(currentCardNumber == termList.size()-2)
            {
                nextButton.setVisibility(View.INVISIBLE);
            }
            currentCardNumber++;
            loadKanji(termList.get(currentCardNumber));
        }
        else if(view.getId()==R.id.showKanjiButton)
        {
            kanjiText.setVisibility(View.VISIBLE);
            showKanjiButton.setVisibility(View.INVISIBLE);
        }
        else if(view.getId()==R.id.showEngHintButton)
        {
            engHintText.setVisibility(View.VISIBLE);
            showEngHintButton.setVisibility(View.INVISIBLE);
        }
        else if(view.getId()==R.id.showJpnsHintButton)
        {
            jpnsHintText.setVisibility(View.VISIBLE);
            showJpnsHintButton.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(drawView.getContext());
        builder.setTitle(getResources().getString(R.string.warningTitle));
        builder.setMessage(getResources().getString(R.string.onBackPressedMessage));
        builder.setNegativeButton(getResources().getString(R.string.cancelLabel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(getResources().getString(R.string.proceedLabel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                KanjiWritingPage.super.onBackPressed();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
