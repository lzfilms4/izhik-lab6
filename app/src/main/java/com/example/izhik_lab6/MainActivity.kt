package com.example.izhik_lab6

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var currentSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val root = findViewById<View>(R.id.main)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val shortSnackbarButton = findViewById<View>(R.id.btnShowShortSnackbar)
        val openJavaButton = findViewById<View>(R.id.btnOpenJavaScreen)
        val dismissButton = findViewById<Button>(R.id.button_dismiss)

        fab.setOnClickListener {
            currentSnackbar = showConfiguredSnackbar(
                parent = root,
                text = getString(R.string.snackbar_long_text)
            )
        }

        shortSnackbarButton.setOnClickListener {
            Snackbar
                .make(root, R.string.snackbar_short_text, Snackbar.LENGTH_SHORT)
                .show()
        }

        openJavaButton.setOnClickListener {
            startActivity(Intent(this, JavaDemoActivity::class.java))
        }

        dismissButton.setOnClickListener {
            currentSnackbar?.dismiss()
        }
    }

    private fun showConfiguredSnackbar(parent: View, text: String): Snackbar {
        return Snackbar
            .make(parent, text, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.snackbar_action_yes) {
                Toast.makeText(this, R.string.toast_action_pressed, Toast.LENGTH_LONG).show()
            }
            .setActionTextColor(getColor(R.color.snackbar_action))
            .also { snackbar ->
                SnackbarStyler.configure(this, snackbar)
                snackbar.addCallback(object : Snackbar.Callback() {
                    override fun onShown(sb: Snackbar?) {
                        Log.d("Lab6", "Snackbar shown on Kotlin screen")
                    }

                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        Log.d("Lab6", "Snackbar dismissed on Kotlin screen, event=$event")
                    }
                })
                snackbar.show()
            }
    }
}
