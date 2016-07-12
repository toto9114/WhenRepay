package com.whenrepay.rnd.whenrepay;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalulatorDialog extends DialogFragment implements View.OnClickListener {


    public CalulatorDialog() {
        // Required empty public constructor
    }

    Button one, two, three, four, five, six, seven, eight, nine, zero, add, sub, mul, div, cancel, equal;
    EditText disp;
    TextView resultView;
    float op1, op2;
    String optr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calulator, container, false);
        one = (Button) view.findViewById(R.id.one);
        two = (Button) view.findViewById(R.id.two);
        three = (Button) view.findViewById(R.id.three);
        four = (Button) view.findViewById(R.id.four);
        five = (Button) view.findViewById(R.id.five);
        six = (Button) view.findViewById(R.id.six);
        seven = (Button) view.findViewById(R.id.seven);
        eight = (Button) view.findViewById(R.id.eight);
        nine = (Button) view.findViewById(R.id.nine);
        zero = (Button) view.findViewById(R.id.zero);
        add = (Button) view.findViewById(R.id.add);
        sub = (Button) view.findViewById(R.id.sub);
        mul = (Button) view.findViewById(R.id.mul);
        div = (Button) view.findViewById(R.id.div);
        cancel = (Button) view.findViewById(R.id.cancel);
        equal = (Button) view.findViewById(R.id.equal);
        disp = (EditText) view.findViewById(R.id.display);
        resultView = (TextView) view.findViewById(R.id.text_result);
        try {
            one.setOnClickListener(this);
            two.setOnClickListener(this);
            three.setOnClickListener(this);
            four.setOnClickListener(this);
            five.setOnClickListener(this);
            six.setOnClickListener(this);
            seven.setOnClickListener(this);
            eight.setOnClickListener(this);
            nine.setOnClickListener(this);
            zero.setOnClickListener(this);
            cancel.setOnClickListener(this);
            add.setOnClickListener(this);
            sub.setOnClickListener(this);
            mul.setOnClickListener(this);
            div.setOnClickListener(this);
            equal.setOnClickListener(this);
        } catch (Exception e) {
        }

        return view;
    }

    public void operation() {
        if (optr.equals("+")) {
            op2 = Float.parseFloat(resultView.getText().toString());
            resultView.setText("");
            op1 = op1 + op2;
            resultView.setText("Result : " + Float.toString(op1));
        } else if (optr.equals("-")) {
            op2 = Float.parseFloat(resultView.getText().toString());
            resultView.setText("");
            op1 = op1 - op2;
            resultView.setText("Result : " + Float.toString(op1));
        } else if (optr.equals("*")) {
            op2 = Float.parseFloat(resultView.getText().toString());
            disp.setText("");
            op1 = op1 * op2;
            resultView.setText("Result : " + Float.toString(op1));
        } else if (optr.equals("/")) {
            op2 = Float.parseFloat(resultView.getText().toString());
            resultView.setText("");
            op1 = op1 / op2;
            resultView.setText("Result : " + Float.toString(op1));
        }
    }


    StringBuilder sb = new StringBuilder();

    @Override
    public void onClick(View v) {
        Editable str = disp.getText();
        switch (v.getId()) {
            case R.id.one:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str.append(one.getText());
                disp.setText(str);
                break;
            case R.id.two:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str.append(two.getText());
                disp.setText(str);
                break;
            case R.id.three:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str.append(three.getText());
                disp.setText(str);
                break;
            case R.id.four:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str.append(four.getText());
                disp.setText(str);
                break;
            case R.id.five:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str.append(five.getText());
                disp.setText(str);
                break;
            case R.id.six:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str.append(six.getText());
                disp.setText(str);
                break;
            case R.id.seven:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str.append(seven.getText());
                disp.setText(str);
                break;
            case R.id.eight:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str.append(eight.getText());
                disp.setText(str);
                break;
            case R.id.nine:
                if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                }
                str.append(nine.getText());
                disp.setText(str);
                break;
            case R.id.cancel:
                op1 = 0;
                op2 = 0;
                disp.setText("");
                break;
            case R.id.add:
                optr = "+";
                if (op1 == 0) {
                    op1 = Float.parseFloat(disp.getText().toString());
                    disp.setText("");
                    sb.append(op1 + "+");
                } else if (op2 != 0) {
                    disp.setText("" + op1);
                } else {
                    op2 = Float.parseFloat(disp.getText().toString());
                    sb.append(op2 + "+");
                    resultView.setText(op2 + "+");
                    op1 = op1 + op2;
                    disp.setText("" + op1);
                }
                resultView.setText(sb);
                break;
            case R.id.sub:
                optr = "-";
                if (op1 == 0) {
                    op1 = Integer.parseInt(disp.getText().toString());
                    disp.setText("");
                } else if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                } else {
                    op2 = Integer.parseInt(disp.getText().toString());
                    disp.setText("");
                    op1 = op1 - op2;
                    disp.setText("Result : " + Float.toString(op1));
                }
                break;
            case R.id.mul:
                optr = "*";
                if (op1 == 0) {
                    op1 = Integer.parseInt(disp.getText().toString());
                    disp.setText("");
                } else if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                } else {
                    op2 = Integer.parseInt(disp.getText().toString());
                    disp.setText("");
                    op1 = op1 * op2;
                    disp.setText("Result : " + Float.toString(op1));
                }
                break;
            case R.id.div:
                optr = "/";
                if (op1 == 0) {
                    op1 = Integer.parseInt(disp.getText().toString());
                    disp.setText("");
                } else if (op2 != 0) {
                    op2 = 0;
                    disp.setText("");
                } else {
                    op2 = Integer.parseInt(disp.getText().toString());
                    disp.setText("");
                    op1 = op1 / op2;
                    disp.setText("Result : " + Float.toString(op1));
                }
                break;
            case R.id.equal:
                if (!optr.equals(null)) {
                    if (op2 != 0) {
                        if (optr.equals("+")) {
                            disp.setText(""); /*op1 = op1 + op2;*/
                            disp.setText("Result : " + Float.toString(op1));
                        } else if (optr.equals("-")) {
                            disp.setText("");/* op1 = op1 - op2;*/
                            disp.setText("Result : " + Float.toString(op1));
                        } else if (optr.equals("*")) {
                            disp.setText("");/* op1 = op1 * op2;*/
                            disp.setText("Result : " + Float.toString(op1));
                        } else if (optr.equals("/")) {
                            disp.setText("");/* op1 = op1 / op2;*/
                            disp.setText("Result : " + Float.toString(op1));
                        }
                    } else {
                        operation();
                    }
                }
                break;
        }
    }
}
