package com.clever.ux.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.clever.ux.ui.MainActivity;

/** @author Aidan Follestad (afollestad) */
public class InvalidLicenseDialog extends DialogFragment
    implements MaterialDialog.SingleButtonCallback {

  public static void show(MainActivity context, boolean allowRetry) {
    InvalidLicenseDialog dialog = new InvalidLicenseDialog();
    Bundle args = new Bundle();
    args.putBoolean("allow_retry", allowRetry);
    dialog.setArguments(args);
    dialog.show(context.getFragmentManager(), "[INVALID_LICENSE_DIALOG]");
  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    MaterialDialog.Builder dialog =
        new MaterialDialog.Builder(getActivity())
            .title(com.clever.ux.R.string.invalid_license)
            .contentLineSpacing(1.2f)
            .cancelable(false);
    if (getArguments() != null && getArguments().getBoolean("allow_retry", false)) {
      dialog
          .positiveText(com.clever.ux.R.string.retry)
          .negativeText(android.R.string.cancel)
          .content(com.clever.ux.R.string.invalid_license_description_retry)
          .onPositive(this);
    } else {
      dialog
          .positiveText(android.R.string.ok)
          .content(
              Html.fromHtml(
                  getString(com.clever.ux.R.string.invalid_license_description, getString(com.clever.ux.R.string.app_name))));
    }
    setCancelable(false);
    return dialog.build();
  }

  @Override
  public void onDismiss(DialogInterface dialog) {
    super.onDismiss(dialog);
    if (getActivity() != null) {
      getActivity().finish();
    }
  }

  @Override
  public void onCancel(DialogInterface dialog) {
    super.onCancel(dialog);
    if (getActivity() != null) {
      getActivity().finish();
    }
  }

  @Override
  public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
    // Retry was clicked
    if (getActivity() != null) {
      ((MainActivity) getActivity()).retryLicenseCheck();
    }
  }
}
