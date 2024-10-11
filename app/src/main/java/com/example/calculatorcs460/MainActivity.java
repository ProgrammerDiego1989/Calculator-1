//import package
package com.example.calculatorcs460;

//import classes
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;


//main method for calculator that implements view onclicklistener
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //define textView for calculator
    TextView resultTV, solutionTV;

    //Define Material Buttons c, open bracket and closed bracket for calculator
    MaterialButton buttonC, buttonBrackOpen, getButtonBrackClose;

    //Define Material buttons for numbers in calculator
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;

    // Define material button for logical operators in calculator
    MaterialButton buttonMul,buttonPlus,buttonSub,buttonDivide,buttonEquals;

    // Define material button AC and dot for calculator
    MaterialButton buttonAC, buttonDot;


    /**
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *  oncreate functions for mul, plus, sub, divide, equals in java and comment
     */


    // create method onCreate for calculator
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        solutionTV = findViewById(R.id.solution_tv);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // create resultTV function for calculator
        resultTV = findViewById(R.id.result_tv);

        // create solutionTV function for calculator
        solutionTV = findViewById(R.id.solution_tv);

        // assignID to button open bracket and closed bracket
        assignID(buttonBrackOpen,R.id.button_open_bracket);
        assignID(getButtonBrackClose,R.id.button_close_bracket);

        // assignID to divide, multiply, plus, subtract, and equals buttons
        assignID(buttonDivide, R.id.button_divide);
        assignID(buttonMul,R.id.button_mul);
        assignID(buttonPlus,R.id.button_plus);
        assignID(buttonSub,R.id.button_sub);
        assignID(buttonEquals,R.id.button_equals);

        // assignID to buttons 0 - 9
        assignID(button0,R.id.button_0);
        assignID(button1,R.id.button_1);
        assignID(button2,R.id.button_2);
        assignID(button3,R.id.button_3);
        assignID(button4,R.id.button_4);
        assignID(button5,R.id.button_5);
        assignID(button6,R.id.button_6);
        assignID(button7,R.id.button_7);
        assignID(button8,R.id.button_8);
        assignID(button9,R.id.button_9);

        // assign button AC, Dot, and C
        assignID(buttonAC,R.id.button_AC);
        assignID(buttonDot,R.id.button_dot);
        assignID(buttonC,R.id.button_c);

    }

    // create material button function for calculator
    void assignID(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);

    }

    // create onclick function for calculator
    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataTocalculate = solutionTV.getText().toString();

        // create if statement for AC button
        if(buttonText.equals("AC")){
            solutionTV.setText("");
            resultTV.setText("0");
            return;
        }   // create if statement function for equals button
        if(buttonText.equals("=")){
            solutionTV.setText(resultTV.getText());
            return;
        }   // create if else statement for C button
        if(buttonText.equals("C")){
            dataTocalculate = dataTocalculate.substring(0, dataTocalculate.length()-1);
        }else{
            dataTocalculate = dataTocalculate+buttonText;
        }

        // set solutionTV to data To calculate
        solutionTV.setText(dataTocalculate);

        // create function for final result with if loop
        String finalResult = getResults(dataTocalculate);
        if(!finalResult.equals("Err")){
            resultTV.setText((finalResult));
        }


    }

    // create function for get results, use try-catch exception for errors
    String getResults(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            return context.evaluateString(scriptable, data, "Javascript", 1, null).toString();

        }catch (Exception e){
            return "Err";
        }
    }
}
