package com.mk.kilikili.utils.manager;

import android.support.v4.app.Fragment;

import com.mk.kilikili.App;
import com.mk.kilikili.module.discovery.DiscoveryFragment;
import com.mk.kilikili.module.home.HomeFragment;
import com.mk.kilikili.module.mine.MineFragment;
import com.mk.kilikili.module.partition.PartitionFragment;
import com.mk.kilikili.module.trends.TrendsFragment;
import com.mk.kilikili.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 常量管理类.
 */

public class C {
    //======RestartableId=======

    public static final int RESTART1 = 1;
    public static final int RESTART2 = 2;
    public static final int RESTART3 = 3;
    public static final int RESTART4 = 4;
    public static final int RESTART5 = 5;
    public static final int RESTART6 = 6;
    public static final int RESTART7 = 7;
    public static final int RESTART8 = 8;
    public static final int RESTART9 = 9;
    public static final int RESTART10 = 10;

    //======Url==================================

    public static final String SHOP_URL = "http://bmall.bilibili.com/";

    public static final String VIP_URL = "http://vip.bilibili.com/site/vip-faq-h5.html#yv1";

    //banner滑动间隔时间
    public static final int BANNER_DELAY = 3;

    //用户投稿
    public static final String USER_CONTRIBUTE = "0";

    //用户收藏夹
    public static final String USER_FAVORITES = "1";

    //用户追番
    public static final String USER_CHASE_BANGUMI = "2";

    //用户兴趣圈
    public static final String USER_INTEREST_QUAN = "3";

    //用户投币
    public static final String USER_COINS = "4";

    //用户游戏
    public static final String USER_PLAY_GAME = "5";

    //用户直播状态
    public static final String USER_LIVE_STATUS = "6";

    //用户mid
    public static final String EXTRA_MID = "extra_mid";

    //用户详情界面传递数据
    public static final String EXTRA_DATA = "extra_data";

    public static final String EXTRA_URL = "url";

    public static final String EXTRA_TITLE = "title";

    public static final String KEY = "login";

    public final static String EXTRA_BANGUMI_KEY = "extra_season_id";

    public static final String SUNDAY_TYPE = "周日";

    public static final String MONDAY_TYPE = "周一";

    public static final String TUESDAY_TYPE = "周二";

    public static final String WEDNESDAY_TYPE = "周三";

    public static final String THURSDAY_TYPE = "周四";

    public static final String FRIDAY_TYEP = "周五";

    public static final String SATURDAY_TYPE = "周六";

    public static final String EXTRA_SPID = "spid";

    public static final String EXTRA_SEASON_ID = "season_id";

    public static final String EXTRA_KEY = "extra_type";

    public static final String EXTRA_ORDER = "extra_order";

    public static final String EXTRA_CID = "cid";

    public static final String EXTRA_ONLINE = "online";

    public static final String EXTRA_FACE = "face";

    public static final String EXTRA_NAME = "name";

    public static final String EXTRA_PARTITION = "extra_partition";

    public static final String TYPE_TOPIC = "weblink";

    public static final String TYPE_ACTIVITY_CENTER = "activity";

    public static final String STYLE_PIC = "gl_pic";

    public static final String EXTRA_CONTENT = "extra_content";

    public static final String AID = "aid";

    public static String EXTRA_AV = "extra_av";

    public static String EXTRA_IMG_URL = "extra_img_url";

    public static final String EXTRA_INFO = "extra_info";

    public static final String VIDEO_TYPE_MP4 = "mp4";

    public static final String SWITCH_MODE_KEY = "mode_key";

    public static final String EXTRA_RID = "extra_rid";

    public static final String EXTRA_POSITION = "extra_pos";

    public static final int ADVERTISING_RID = 165;

    public final static ArrayList<Class<? extends Fragment>> MAIN_FRAGMENT = new ArrayList<>();

    static {
        MAIN_FRAGMENT.add(HomeFragment.class);
        MAIN_FRAGMENT.add(PartitionFragment.class);
        MAIN_FRAGMENT.add(TrendsFragment.class);
        MAIN_FRAGMENT.add(DiscoveryFragment.class);
        MAIN_FRAGMENT.add(MineFragment.class);
    }

    public final static Map<String, Class<? extends Fragment>> MAIN_FRAGMENT_MAP = new HashMap<>();

    static {
        MAIN_FRAGMENT_MAP.put(HomeFragment.class.getSimpleName(), HomeFragment.class);
        MAIN_FRAGMENT_MAP.put(PartitionFragment.class.getSimpleName(), PartitionFragment.class);
        MAIN_FRAGMENT_MAP.put(TrendsFragment.class.getSimpleName(), TrendsFragment.class);
        MAIN_FRAGMENT_MAP.put(DiscoveryFragment.class.getSimpleName(), DiscoveryFragment.class);
        MAIN_FRAGMENT_MAP.put(MineFragment.class.getSimpleName(), MineFragment.class);
    }

    private final static int CARD_CORNER = 2;
    public final static float[] CARD_CORNERS = {
            DisplayUtil.dp2px(App.getInstance(), CARD_CORNER)
            , DisplayUtil.dp2px(App.getInstance(), CARD_CORNER)
            , DisplayUtil.dp2px(App.getInstance(), CARD_CORNER)
            , DisplayUtil.dp2px(App.getInstance(), CARD_CORNER)
            , DisplayUtil.dp2px(App.getInstance(), 0)
            , DisplayUtil.dp2px(App.getInstance(), 0)
            , DisplayUtil.dp2px(App.getInstance(), 0)
            , DisplayUtil.dp2px(App.getInstance(), 0)
    };
}
