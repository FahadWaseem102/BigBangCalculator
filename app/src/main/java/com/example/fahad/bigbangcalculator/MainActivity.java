package com.example.fahad.bigbangcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fahad.bigbangcalculator.AppConstants.AppConstants;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    TextView textExpression, textResult ;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,b_add,b_minus,b_multiply,b_divide,
           b_open,b_close,b_CE,b_clear,b_dott,b_equal ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }

    private void bindViews(){
        textResult=findViewById(R.id.txtResult);
        textExpression=findViewById(R.id.txtExpressions);

        b0=findViewById(R.id.btn0);
        b1=findViewById(R.id.btn1);
        b2=findViewById(R.id.btn2);
        b3=findViewById(R.id.btn3);
        b4=findViewById(R.id.btn4);
        b5=findViewById(R.id.btn5);
        b6=findViewById(R.id.btn6);
        b7=findViewById(R.id.btn7);
        b8=findViewById(R.id.btn8);
        b9=findViewById(R.id.btn9);
        b_add=findViewById(R.id.btn_Add);
        b_minus=findViewById(R.id.btn_Minus);
        b_multiply=findViewById(R.id.btn_multiply);
        b_divide=findViewById(R.id.btn_divide);
        b_open=findViewById(R.id.btn_Open);
        b_close=findViewById(R.id.btn_Close);
        b_CE=findViewById(R.id.btnCE);
        b_clear=findViewById(R.id.btn_CLR);
        b_dott=findViewById(R.id.btn_dott);
        b_equal=findViewById(R.id.btn_Equal);

        b0.setOnClickListener(new NumberButtonClickListener());
        b1.setOnClickListener(new NumberButtonClickListener());
        b2.setOnClickListener(new NumberButtonClickListener());
        b3.setOnClickListener(new NumberButtonClickListener());
        b4.setOnClickListener(new NumberButtonClickListener());
        b5.setOnClickListener(new NumberButtonClickListener());
        b6.setOnClickListener(new NumberButtonClickListener());
        b7.setOnClickListener(new NumberButtonClickListener());
        b8.setOnClickListener(new NumberButtonClickListener());
        b9.setOnClickListener(new NumberButtonClickListener());
        b_dott.setOnClickListener(new NumberButtonClickListener());

        b_CE.setOnClickListener(new ActionButtonClickListener());
        b_clear.setOnClickListener(new ActionButtonClickListener());
        b_equal.setOnClickListener(new ActionButtonClickListener());

        b_add.setOnClickListener(new OperationButtonClickListener());
        b_minus.setOnClickListener(new OperationButtonClickListener());
        b_divide.setOnClickListener(new OperationButtonClickListener());
        b_multiply.setOnClickListener(new OperationButtonClickListener());
        b_open.setOnClickListener(new OperationButtonClickListener());
        b_close.setOnClickListener(new OperationButtonClickListener());

    }

    class ActionButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_CLR :
                    String string = textExpression.getText().toString() ;
                    if(!string.isEmpty()){
                        textExpression.setText(string.substring(0,string.length()-1));
                    }
                    textResult.setText("");
                    break;

                case R.id.btnCE:
                    textExpression.setText("");
                    textResult.setText("");
                    break;

                case R.id.btn_Equal:
                    try {
                        Expression expression = new ExpressionBuilder(textExpression.getText().toString()).build();
                        double result=expression.evaluate();
                        textResult.setText(Double.toString(result));
                    } catch (Exception ex){
                        textExpression.setText(AppConstants.MESSAGE_ERROR);
                        Log.d("Exception"," message : "+ex.getMessage()) ;
                    }
                    break;
            }

        }
    }
    class NumberButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            appendExpressions(v,true);
        }
    }
    class OperationButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            appendExpressions(v,false);
        }
    }

    private void appendExpressions(View view, boolean canCl){
        Button btn = (Button) view;
        if(textExpression.getText().toString().equals(AppConstants.MESSAGE_ERROR))
            textExpression.setText("");
        if (!textResult.getText().toString().isEmpty())
            textExpression.setText("");
        if (canCl){
            textResult.setText("");
            textExpression.setText(textExpression.getText().toString() + btn.getText().toString());
        }else{
            textExpression.setText(textExpression.getText().toString() + textResult.getText().toString()+ btn.getText().toString());
            textResult.setText("");
        }
    }

}
