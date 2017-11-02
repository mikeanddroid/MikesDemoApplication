package com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.demohome
 * Created by GiveMeWingzzz on 10/18/2017.
 */

public class DemoUtils {

    public static class Constants {

        public static final String GAMING_TYPE = "GAMING TYPE";
        public static final String LANDSCAPE_TYPE = "LANDSCAPE TYPE";
        public static final String RANDOM_TYPE = "RANDOM TYPE";
        public static final String OTHERS_TYPE = "OTHERS TYPE";

        public static final String VIEW_TYPE_LOADING = "VIEW_TYPE_LOADING";

        public static final int NULL_TYPE_INDEX = 1;
        public static final int GAMING_TYPE_INDEX = 1;
        public static final int LANDSCAPE_TYPE_INDEX = 2;
        public static final int RANDOM_TYPE_INDEX = 3;
        public static final int OTHERS_TYPE_INDEX = 4;

        public static final int CATEGORY_TYPE_INDEX = 5;

        public static final int VIEW_TYPE_LOADING_INDEX = 0;

        public static List<String> getRandomImageType() {

            List<String> imageListTypeList = new ArrayList<>();

            imageListTypeList.add(GAMING_TYPE);
            imageListTypeList.add(LANDSCAPE_TYPE);
            imageListTypeList.add(RANDOM_TYPE);
            imageListTypeList.add(OTHERS_TYPE);

            return imageListTypeList;
        }

    }

    public static List<String> imageUrls() {

        List<String> imageUrlList = new ArrayList<>();

        imageUrlList.add("https://i.imgur.com/LGcLYM1.jpg");
        imageUrlList.add("https://i.imgur.com/iVTyEWe.jpg");
        imageUrlList.add("https://i.imgur.com/knPk82Q.jpg");
        imageUrlList.add("https://i.imgur.com/NPyhcB1.jpg");
        imageUrlList.add("https://i.imgur.com/zKFnpb0.jpg");
        imageUrlList.add("https://i.imgur.com/6TcpDoY.jpg");
        imageUrlList.add("https://i.imgur.com/rfqcdls.jpg");
        imageUrlList.add("https://i.imgur.com/3fNw7qc.jpg");
        imageUrlList.add("https://i.imgur.com/essNDXP.jpg");
        imageUrlList.add("https://i.imgur.com/2P6Dunf.jpg");
        imageUrlList.add("https://i.imgur.com/ChzyYBQ.jpg");
        imageUrlList.add("https://i.imgur.com/9fQ2WMF.jpg");
        imageUrlList.add("https://i.imgur.com/upf7yGu.jpg");
        imageUrlList.add("https://i.imgur.com/1YSbRfo.jpg");
        imageUrlList.add("https://i.imgur.com/4ksKrsq.jpg");
        imageUrlList.add("https://i.imgur.com/P9oXFsw.jpg");
        imageUrlList.add("https://i.imgur.com/QsnAN3d.jpg");
        imageUrlList.add("https://i.imgur.com/wdNAlBI.jpg");
        imageUrlList.add("https://i.imgur.com/enyy7vl.jpg");
        imageUrlList.add("https://i.imgur.com/4rxdtMb.jpg");
        imageUrlList.add("https://i.imgur.com/JNX2q7W.jpg");
        imageUrlList.add("https://i.imgur.com/QAiqYAs.jpg");

        // New Urls from here
        imageUrlList.add("https://i.imgur.com/cfKwpAo.jpg");
        imageUrlList.add("https://i.imgur.com/V3T0yUD.jpg");
        imageUrlList.add("https://i.imgur.com/0GUUNOJ.jpg");
        imageUrlList.add("https://i.imgur.com/Ld5E9Eg.jpg");
        imageUrlList.add("https://i.imgur.com/2TOwmX8.jpg");
        imageUrlList.add("https://i.imgur.com/fUz8xtR.jpg");
        imageUrlList.add("https://i.imgur.com/hwBUZ1T.jpg");
        imageUrlList.add("https://i.imgur.com/jHHtGaf.jpg");
        imageUrlList.add("https://i.imgur.com/6zZBHoP.jpg");
        imageUrlList.add("https://i.imgur.com/vF6B8Y0.jpg");
        imageUrlList.add("https://i.imgur.com/p29e8Wg.jpg");
        imageUrlList.add("https://i.imgur.com/PK7Lkyu.jpg");// ..
        imageUrlList.add("https://i.imgur.com/5ozZJb0.jpg");
        imageUrlList.add("https://i.imgur.com/SP7Sw8M.jpg");
        imageUrlList.add("https://i.imgur.com/2IpKdI7.jpg");
        imageUrlList.add("https://i.imgur.com/eWHd3BG.jpg");
        imageUrlList.add("https://i.imgur.com/99Sfyb7.jpg");
        imageUrlList.add("https://i.imgur.com/N5q0iC4.jpg");
        imageUrlList.add("https://i.imgur.com/8rnFLUa.jpg");
        imageUrlList.add("https://i.imgur.com/HL0lOsX.jpg");
        imageUrlList.add("https://i.imgur.com/TjEdk8c.jpg");
        imageUrlList.add("https://i.imgur.com/M6j4ENP.jpg");
        imageUrlList.add("https://i.imgur.com/4jnTKnY.jpg");
        imageUrlList.add("https://i.imgur.com/psyWYrz.jpg");
        imageUrlList.add("https://i.imgur.com/S5fuTtr.jpg");
        imageUrlList.add("https://i.imgur.com/uqxEk3x.jpg");
        imageUrlList.add("https://i.imgur.com/a74WZwj.jpg");
        imageUrlList.add("https://i.imgur.com/JgVmkyD.jpg");
        imageUrlList.add("https://i.imgur.com/bb7uQHq.jpg");
        imageUrlList.add("https://i.imgur.com/upf7yGu.jpg");
        imageUrlList.add("https://i.imgur.com/sQQZlPg.jpg");
        imageUrlList.add("https://i.imgur.com/gDI5bcz.jpg");
        imageUrlList.add("https://i.imgur.com/BBMzZk8.jpg");
        imageUrlList.add("https://i.imgur.com/uUH2wxd.jpg");
        imageUrlList.add("https://i.imgur.com/Rqq5rla.jpg");
        imageUrlList.add("https://i.imgur.com/zbQAY7I.jpg");
        imageUrlList.add("https://i.imgur.com/JOt8bcN.jpg");
        imageUrlList.add("https://i.imgur.com/u3pR3V7.jpg");
        imageUrlList.add("https://i.imgur.com/3entavI.jpg");
        imageUrlList.add("https://i.imgur.com/SDQmy2o.jpg");
        imageUrlList.add("https://i.imgur.com/JkJeLZF.jpg");
        imageUrlList.add("https://i.imgur.com/TNuVvUX.png");
        imageUrlList.add("https://i.imgur.com/dCS4tQk.jpg");
        imageUrlList.add("https://i.imgur.com/9wHVhck.jpg");
        imageUrlList.add("https://i.imgur.com/SpBfUZi.jpg");
        imageUrlList.add("https://i.imgur.com/n2JY9jj.jpg");
        imageUrlList.add("https://i.imgur.com/JKNnuSE.jpg");
        imageUrlList.add("https://i.imgur.com/YwAmPYu.jpg");
        imageUrlList.add("https://i.imgur.com/T7NBjJm.jpg");
        imageUrlList.add("https://i.imgur.com/ujJ3g1k.jpg");
        imageUrlList.add("https://i.imgur.com/ftY0903.jpg");
        imageUrlList.add("https://i.imgur.com/zfU3vsM.jpg");
        imageUrlList.add("https://i.imgur.com/rL3PWMa.jpg");
        imageUrlList.add("https://i.imgur.com/TQA4Rnx.jpg");
        imageUrlList.add("https://i.imgur.com/qfwPzwU.jpg");
        imageUrlList.add("https://i.imgur.com/uiIEsaX.jpg");
        imageUrlList.add("https://i.imgur.com/2Vizf4g.jpg");
        imageUrlList.add("https://i.imgur.com/ZT1Ptmd.jpg");
        imageUrlList.add("https://i.imgur.com/OR2AlPp.jpg");
        imageUrlList.add("https://i.imgur.com/EbS56zD.jpg");
        imageUrlList.add("https://i.imgur.com/jb4UHBi.jpg");
        imageUrlList.add("https://i.imgur.com/ziOFAGJ.png");
        imageUrlList.add("https://i.imgur.com/5RQKwD2.png");
        imageUrlList.add("https://i.imgur.com/h8EpGvU.jpg");
        imageUrlList.add("https://i.imgur.com/Cyu5WeM.jpg");
        imageUrlList.add("https://i.imgur.com/Cyu5WeM.jpg");

        return imageUrlList;

    }

    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        /* Min + (int)(Math.random() * ((Max - Min) + 1)) */
        int randNo = min + (int) (Math.random() * ((max - min) + 1));

        return randNo;
    }

}
