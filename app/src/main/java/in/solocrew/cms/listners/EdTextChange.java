package in.solocrew.cms.listners;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class EdTextChange implements TextWatcher {

    private Refresh refresh;
    public EdTextChange(Context context) {
        refresh = (Refresh) context;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        refresh.refresh();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public interface Refresh{
        void refresh();
    }
}
