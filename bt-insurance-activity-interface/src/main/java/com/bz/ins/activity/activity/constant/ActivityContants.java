package com.bz.ins.activity.activity.constant;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 2:05 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class ActivityContants {

    /**
     * 活动信息缓存key前缀
     */
    private final static String ACTIVITY_MESSAGE_CACHE_KEY = "bz_insurance_activity_message_cache_key_";


    /**
     * 废弃
     */
    private static final String ACTIVITY_QUESTION_LIST_CACHE_KEY = "bz_insurance_activity_question_list_cache_key_";


    /**
     * 活动与期数对应缓存
     */
    private static final String ACTIVITY_SEASON_CAHCHE_KEY = "bz_insurance_activity_activity_season_cache_key_";

    /**
     * 活动当前其实缓存
     */
    private static final String ACTIVITY_CURRENT_SEASON_CACHE_KEY = "bz_insurance_activity_current_season_cache_key_";

    /**
     * 活动缓存信息key
     * @param activityID 活动id
     * @param seasonID seasonID
     * @return
     */
    public static String getActivityMessageCacheKey(Integer activityID, Integer seasonID) {
        return ACTIVITY_MESSAGE_CACHE_KEY + activityID + "_" + seasonID;
    }

    /**
     * 活动题库缓存cache
     * @param activityID
     * @param seasonID
     * @return
     */
    public static String getActivityQuestionListCacheKey(Integer activityID, Integer seasonID) {
        return ACTIVITY_QUESTION_LIST_CACHE_KEY + activityID + "_" + seasonID;
    }

    /**
     * 获取活动与期数对应缓存
     * @param activityID
     * @return
     */
    public static String getActivitySeasonCahcheKey(Integer activityID) {
        return ACTIVITY_SEASON_CAHCHE_KEY + activityID;
    }

    public static String getActivityCurrentSeasonCacheKey(Integer activity) {
        return ACTIVITY_SEASON_CAHCHE_KEY + activity;
    }

}
