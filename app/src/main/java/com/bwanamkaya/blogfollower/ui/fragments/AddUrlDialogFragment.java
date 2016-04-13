package com.bwanamkaya.blogfollower.ui.fragments;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.bwanamkaya.blogfollower.R;
import com.bwanamkaya.blogfollower.bus.BlogAddEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddUrlDialogFragment extends DialogFragment {
    @Bind(R.id.blog_add_name)
    EditText blog_add_name;
    @Bind(R.id.blog_add_link)
    EditText blog_add_link;

    BlogAddEvent blogAddEvent;

    public AddUrlDialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = View.inflate(getActivity(), R.layout.fragment_add_url_dialog, null);
        ButterKnife.bind(this, view);
        builder.setView(view)
                .setPositiveButton(R.string.add_blog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String blog_name = blog_add_name.getText().toString();
                        String blog_url = blog_add_link.getText().toString();
                        blogAddEvent = new BlogAddEvent(blog_name, blog_url);
                        EventBus.getDefault().post(blogAddEvent);
                    }
                })
                .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
        return builder.create();
    }
}
