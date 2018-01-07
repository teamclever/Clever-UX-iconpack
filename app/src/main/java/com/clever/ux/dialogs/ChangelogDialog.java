package com.clever.ux.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.clever.ux.adapters.BulletPointListViewAdapter;

/** @author Aidan Follestad (afollestad) */
public class ChangelogDialog extends DialogFragment {

  public static void show(AppCompatActivity context) {
    Fragment frag = context.getSupportFragmentManager().findFragmentByTag("POINTS_EARNED_TUTORIAL");
    if (frag != null) {
      ((ChangelogDialog) frag).dismiss();
    }
    new ChangelogDialog().show(context.getSupportFragmentManager(), "POINTS_EARNED_TUTORIAL");
  }

  @SuppressLint("InflateParams")
  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    return new MaterialDialog.Builder(getActivity())
        .title(com.clever.ux.R.string.changelog)
        .titleGravity(GravityEnum.CENTER)
        .titleColorAttr(com.clever.ux.R.attr.colorAccent)
        .adapter(new BulletPointListViewAdapter(getActivity(), com.clever.ux.R.array.changelog), null)
        .positiveText(com.clever.ux.R.string.cool)
        .build();
  }
}
