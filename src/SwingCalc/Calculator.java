package SwingCalc;
import javax.swing.JFrame;//레이아웃/이벤트 처리
import javax.swing.JTextField;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame{
    private JTextField inputSpace;
    private String num="";
    private String prev_operation="";
    private ArrayList<String> equation=new ArrayList<String>();
    public Calculator(){
        setLayout(null);
        inputSpace=new JTextField();
        inputSpace.setEditable(false);
        inputSpace.setBackground(Color.WHITE);
        inputSpace.setHorizontalAlignment(JTextField.RIGHT);
        inputSpace.setFont(new Font("Arial",Font.BOLD,50));
        inputSpace.setBounds(8,10,270,70);

        //버튼을 만들 패널
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,4,10,10));
        buttonPanel.setBounds(8,90,270,235);
        //계산기 버튼의 글자를 차례대로 배열에 저장
        String button_names[] = { "C", "/", "*", "=", "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "0" };
        JButton buttons[] = new JButton[button_names.length];

        //배열을 이용해 버튼 생성
        for(int i=0;i<button_names.length;i++){
            buttons[i]=new JButton(button_names[i]);
            buttons[i].setFont(new Font("Arial",Font.BOLD,20));
            if(button_names[i]=="C") buttons[i].setBackground(Color.RED);
            else if((i>=4&&i<=6) || (i>=8&&i<=10) || (i>=12&&i<=14)) buttons[i].setBackground(Color.BLACK);
            else buttons[i].setBackground(Color.GRAY);
            //글자색 지정
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setBorderPainted(false);
            buttons[i].addActionListener(new PadActionListener());
            buttonPanel.add(buttons[i]);
        }
        add(inputSpace);
        add(buttonPanel);
        //창의 제목,사이즈,보이기 여부 지정
        setTitle("계산기");
        setVisible(true);
        setSize(300,370);
        setLocationRelativeTo(null);
        setResizable(true);
        //창을 닫을 때 실행 중인 프로그램도 같이 종료
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    class PadActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String operation = e.getActionCommand();
            if(operation.equals("C")){
                inputSpace.setText("");
            }
            else if(operation.equals("=")){
                String result = Double.toString(calculate(inputSpace.getText()));
                inputSpace.setText(""+result);
                num="";
            }
            else if(operation.equals("+") ||operation.equals("-")||operation.equals("*")||operation.equals("/")){
                if(inputSpace.getText().equals("")&&operation.equals("-")){
                    inputSpace.setText(inputSpace.getText()+e.getActionCommand());
                }
                else if(!inputSpace.getText().equals("")&&!prev_operation.equals("+")&&!prev_operation.equals("-")&&!prev_operation.equals("*")&&!prev_operation.equals("/")){
                    inputSpace.setText(inputSpace.getText()+e.getActionCommand());
                }
            }
            else{
                inputSpace.setText(inputSpace.getText()+e.getActionCommand());
            }
            prev_operation=e.getActionCommand();
        }
    }
    private void fullTextParsing(String inputText){
        equation.clear();

        for(int i=0;i<inputText.length();i++){
            char ch = inputText.charAt(i);
            if(ch=='-' || ch=='+' ||ch=='*'||ch=='/'){
                equation.add(num);
                num="";
                equation.add(ch+"");
            }
            else{
                num=num+ch;
            }
        }equation.add(num);
        equation.remove("");
    }
    public double calculate(String inputText){
        fullTextParsing(inputText);
        //위의 메소드를 실행하면 ArrayList에 숫자와 연산 기호가 담김
        double prev=0;double current=0;
        String mode="";

        for(int i=0;i<equation.size();i++){
            String s=equation.get(i);
            if (s.equals("+")) {
                mode = "add";
            } else if (s.equals("-")) {
                mode = "sub";
            }
            else if (s.equals("*")) {
                mode = "mul";
            }
            else if (s.equals("/")) {
                mode = "div";
            }  else {
                if ((mode.equals("mul") || mode.equals("div")) && !s.equals("+") && !s.equals("-") && !s.equals("×") && !s.equals("÷")) {
                    Double one = Double.parseDouble(equation.get(i - 2));
                    Double two = Double.parseDouble(equation.get(i));
                    Double result = 0.0;

                    //mode에 따라서 계산
                    if (mode.equals("mul")) {
                        result = one * two;
                    } else if (mode.equals("div")) {
                        result = one / two;
                    }
                    //result값을 ArrayList에 추가
                    equation.add(i + 1, Double.toString(result));

                    for (int j = 0; j < 3; j++) {
                        equation.remove(i - 2);
                    }

                    //예를 들어 3 + 5 x 6에서  3 + 30이 되었으므로 인덱스를 2만큼 되돌아감
                    i -= 2;	// 결과값이 생긴 인덱스로 이동
                }
            }
        }	// 곱셈 나눗셈을 먼저 계산한다

        //+일경우 add, -일경우 sub
        for (String s : equation) {
            if (s.equals("+")) {
                mode = "add";
            } else if (s.equals("-")) {
                mode = "sub";

                //곱셈, 나눗셈 연산 삭제됨
            }  else {
                //숫자일 경우 문자열을 Double로 형변환
                current = Double.parseDouble(s);

                //mode값에 따라 처리, prev는 계속 계산값이 갱신됨
                if (mode.equals("add")) {
                    prev += current;
                } else if (mode.equals("sub")) {
                    prev -= current;
                } else {
                    prev = current;
                }
            }
            //소수점 여섯번 째 자리에서 반올림 -> 화면 표시 제한이 있기때문
            prev = Math.round(prev * 100000) / 100000.0;
        }
        //값 반환
        return prev;
    }
    public static void main(String[] args){
        new Calculator();
    }
}
