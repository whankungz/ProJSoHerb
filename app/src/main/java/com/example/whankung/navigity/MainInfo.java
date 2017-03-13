package com.example.whankung.navigity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Whankung on 12/3/2560.
 */
public class MainInfo extends Fragment {
    private View rootView;
    private Typeface font;
    private TextView tv, im, im2, im3, im4, im5, im6, p, p2, p3, p4, p5, p6, st, st2, st3, st4, st5, st6;
    private ImageView img, i, i2, i3, i4, i5, i6, sta, sta2, sta3, sta4, sta5, sta6;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStat) {
        rootView = inflater.inflate(R.layout.stucture_info, container, false);
        setView();
        setInfo();


        return rootView;
    }

    private void setView() {
        st = (TextView) rootView.findViewById(R.id.st);
        st2 = (TextView) rootView.findViewById(R.id.st2);
        st3 = (TextView) rootView.findViewById(R.id.st3);
        st4 = (TextView) rootView.findViewById(R.id.st4);
        st5 = (TextView) rootView.findViewById(R.id.st5);
        st6 = (TextView) rootView.findViewById(R.id.st6);

        sta = (ImageView) rootView.findViewById(R.id.sta);
        sta2 = (ImageView) rootView.findViewById(R.id.sta2);
        sta3 = (ImageView) rootView.findViewById(R.id.sta3);
        sta4 = (ImageView) rootView.findViewById(R.id.sta4);
        sta5 = (ImageView) rootView.findViewById(R.id.sta5);
        sta6 = (ImageView) rootView.findViewById(R.id.sta6);


        img = (ImageView) rootView.findViewById(R.id.action_login);
        i = (ImageView) rootView.findViewById(R.id.img);
        i2 = (ImageView) rootView.findViewById(R.id.img2);
        i3 = (ImageView) rootView.findViewById(R.id.img3);
        i4 = (ImageView) rootView.findViewById(R.id.img4);
        i5 = (ImageView) rootView.findViewById(R.id.img5);
        i6 = (ImageView) rootView.findViewById(R.id.img6);
        im = (TextView) rootView.findViewById(R.id.im);
        im2 = (TextView) rootView.findViewById(R.id.im2);
        im3 = (TextView) rootView.findViewById(R.id.im3);
        im4 = (TextView) rootView.findViewById(R.id.im4);
        im5 = (TextView) rootView.findViewById(R.id.im5);
        im6 = (TextView) rootView.findViewById(R.id.im6);
        p = (TextView) rootView.findViewById(R.id.p);
        p2 = (TextView) rootView.findViewById(R.id.p2);
        p3 = (TextView) rootView.findViewById(R.id.p3);
        p4 = (TextView) rootView.findViewById(R.id.p4);
        p5 = (TextView) rootView.findViewById(R.id.p5);
        p6 = (TextView) rootView.findViewById(R.id.p6);


//        เปลี่ยนfont
        font = Typeface.createFromAsset(getContext().getAssets(), "tmedium.ttf");

        im.setTypeface(font);
        im2.setTypeface(font);
        im3.setTypeface(font);
        im4.setTypeface(font);
        im5.setTypeface(font);
        im6.setTypeface(font);
        p.setTypeface(font);
        p2.setTypeface(font);
        p3.setTypeface(font);
        p4.setTypeface(font);
        p5.setTypeface(font);
        p6.setTypeface(font);
        st.setTypeface(font);
        st2.setTypeface(font);
        st3.setTypeface(font);
        st4.setTypeface(font);
        st5.setTypeface(font);
        st6.setTypeface(font);


//        img.setVisibility(View.VISIBLE);
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                startActivity(intent);
//
//            }
//        });


        i.setClickable(true);
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.container, new DataInfo());
                transaction.commit();
            }
        });
    }

    private void setInfo() {
        sta.setImageResource(R.drawable.ic_star2);
        sta2.setImageResource(R.drawable.ic_star2);
        sta3.setImageResource(R.drawable.ic_star2);
        sta4.setImageResource(R.drawable.ic_star2);
        sta5.setImageResource(R.drawable.ic_star2);
        sta6.setImageResource(R.drawable.ic_star2);

        i.setImageResource(R.drawable.img_in_big);

        i2.setImageResource(R.drawable.img_in_big2);
        i3.setImageResource(R.drawable.img_in_big3);
        i4.setImageResource(R.drawable.img_in_big4);
        i5.setImageResource(R.drawable.img_in_big5);
        i6.setImageResource(R.drawable.img_in_big6);
        im.setText("7ข้อที่ชาวไร่ยาสูบ ยังได้รับ-ทำได้");
        im2.setText("สงกรานต์ปลอดภัย ดื่มไม่ขับ");
        im3.setText("9 วิธีเอาตัวรอดแบบไม่เสียเพื่อน");
        im4.setText("สาเหตุหลักของการเกิดอุบัติเหตุ");
        im5.setText("ลดความเร็ว ลดความเสี่ยง");
        im6.setText("อาการเส้นเลือดในสมองตีบ");
    }
}
