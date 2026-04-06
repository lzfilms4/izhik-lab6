package com.example.izhik_lab6;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class JavaDemoActivity extends AppCompatActivity {

    @Nullable
    private Snackbar currentSnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_demo);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View root = findViewById(R.id.javaRoot);
        FloatingActionButton fab = findViewById(R.id.fab);
        Button dismissButton = findViewById(R.id.button_dismiss);
        View backButton = findViewById(R.id.btnBackToKotlin);

        fab.setOnClickListener(view -> currentSnackbar = showConfiguredSnackbar(root));

        dismissButton.setOnClickListener(view -> {
            if (currentSnackbar != null) {
                currentSnackbar.dismiss();
            }
        });

        backButton.setOnClickListener(view -> finish());
    }

    private Snackbar showConfiguredSnackbar(View parent) {
        Snackbar snackbar = Snackbar
            .make(parent, R.string.snackbar_java_text, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.snackbar_action_yes, view ->
                Toast.makeText(
                    JavaDemoActivity.this,
                    R.string.toast_action_pressed,
                    Toast.LENGTH_LONG
                ).show()
            );

        snackbar.setActionTextColor(getColor(R.color.snackbar_action));
        SnackbarStyler.configure(this, snackbar);
        snackbar.addCallback(new Snackbar.Callback() {
            @Override
            public void onShown(Snackbar sb) {
                Log.d("Lab6", "Snackbar shown on Java screen");
            }

            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                Log.d("Lab6", "Snackbar dismissed on Java screen, event=" + event);
            }
        });
        snackbar.show();
        return snackbar;
    }
}
