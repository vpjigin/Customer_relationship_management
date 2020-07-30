package in.solocrew.cms.util;

import android.widget.EditText;

public class Text {
    private EditText ed;

    public Text(EditText ed){
        this.ed = ed;
    }

    public String toText(){
        return ed.getText().toString();
    }

    public int toInt(){
        String text = toText();
        if(text.isEmpty()) return 0;

        return Integer.parseInt(toText());
    }

    public double toDouble(){
        String text = toText();
        if(text.isEmpty()) return 0;

        return Double.parseDouble(toText());
    }

}
