package com.lick.model.entity;

import java.io.Serializable;
import java.util.Date;

/**付费问答(有氧阅读)信息
 * Created by lichengkai on 2017/9/18.
 */
public class QaContent implements Serializable {

    private static final long serialVersionUID = -4960828029068416161L;

    private long      id;                      //主键id
    private String    contentCode;             //付费内容编码（平台统一）
    private String    isvCode;                 //isv编码
    private String    isvName;                 //isv名称
    private String    contentIsvCode;          //isv内容编码
    private String    contentType;             //内容类型 QA：问答、KNOWLEDGE：知识、COURSE：课程
    private String    category;                //细分类别 LISTEN：旁听、WATCH：旁看、OTHER：其他
    private String    title;                   //内容标题
    private String    summary;                 //内容摘要
    private String    images;                  //内容图片，以JSON格式存放图片URL，最多5张
    private Double    originPrice;             //付费内容原始价格，精确到2位小数。必须大于等于0
    private Double    price;                   //实际价格，精确到2位小数。必须大于等于0
    private String    doctorIsvCode;           //isv的医生编码
    private String    additionalContent;       //补充说明（如：旁看、旁听、限时免费、特惠等字眼）
    private long      readCount;               //阅读数
    private long      originReadCount;         //阅读数初始值
    private long      likedCount;              //点赞数
    private long      originLikedCount;        //点赞数初始值
    private String    isFree;                  //Y：免费，N：付费，默认N
    private Date      freeStarttime;           //免费开始时间
    private Date      freeEndtime;             //免费截止时间
    private String    linkUrl;                 //第三方详情页URL
    private int    duration;                //多媒体播放时长 M标识分钟，S标识秒，如50M、60S
    private String    mediaUrl;                //多媒体资源URL
    private String    sex;                     //适用人性别 M：男、F:女
    private String    age;                     //适用者年龄 7岁以内“Y岁M月D天”
    private String    status;                  //发布状态 publish：已发布、revoke 撤销发布
    private String    twoDeptInfo;             //二级科室冗余信息
    private String    sicknessInfo;            //疾病冗余信息
    private String    doctorInfo;              //专家冗余信息
    private String    isTop;                   //是否置顶
    private Date      topStarttime;            //置顶开始时间
    private Date      topEndtime;              //置顶结束时间
    private String    isPlatformCharge;        //是否平台收费 Y：是、N：否
    private String    adPosition;              //首页推荐、运营广告位
    private String    adPositionTwo;           //首页推荐、运营广告位2
    private int       pvTotal1;                //pv1,7天
    private int       pvTotal2;                //pv2,30天
    private String    isHasPage;               //是否有中间页
    private Date      gmtCreate;               //创建时间
    private Date      gmtModify;               //修改时间

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getIsHasPage() {
        return isHasPage;
    }

    public void setIsHasPage(String isHasPage) {
        this.isHasPage = isHasPage;
    }

    public int getPvTotal1() {
        return pvTotal1;
    }

    public void setPvTotal1(int pvTotal1) {
        this.pvTotal1 = pvTotal1;
    }

    public int getPvTotal2() {
        return pvTotal2;
    }

    public void setPvTotal2(int pvTotal2) {
        this.pvTotal2 = pvTotal2;
    }

    public String getAdPosition() {
        return adPosition;
    }

    public void setAdPosition(String adPosition) {
        this.adPosition = adPosition;
    }

    public String getAdPositionTwo() {
        return adPositionTwo;
    }

    public void setAdPositionTwo(String adPositionTwo) {
        this.adPositionTwo = adPositionTwo;
    }

    public String getIsPlatformCharge() {
        return isPlatformCharge;
    }

    public void setIsPlatformCharge(String isPlatformCharge) {
        this.isPlatformCharge = isPlatformCharge;
    }

    public String getIsvCode() {
        return isvCode;
    }

    public void setIsvCode(String isvCode) {
        this.isvCode = isvCode;
    }

    public String getIsvName() {
        return isvName;
    }

    public void setIsvName(String isvName) {
        this.isvName = isvName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContentCode() {
        return contentCode;
    }

    public void setContentCode(String contentCode) {
        this.contentCode = contentCode;
    }

    public String getContentIsvCode() {
        return contentIsvCode;
    }

    public void setContentIsvCode(String contentIsvCode) {
        this.contentIsvCode = contentIsvCode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(Double originPrice) {
        this.originPrice = originPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDoctorIsvCode() {
        return doctorIsvCode;
    }

    public void setDoctorIsvCode(String doctorIsvCode) {
        this.doctorIsvCode = doctorIsvCode;
    }

    public String getAdditionalContent() {
        return additionalContent;
    }

    public void setAdditionalContent(String additionalContent) {
        this.additionalContent = additionalContent;
    }

    public long getReadCount() {
        return readCount;
    }

    public void setReadCount(long readCount) {
        this.readCount = readCount;
    }

    public long getOriginReadCount() {
        return originReadCount;
    }

    public void setOriginReadCount(long originReadCount) {
        this.originReadCount = originReadCount;
    }

    public long getLikedCount() {
        return likedCount;
    }

    public void setLikedCount(long likedCount) {
        this.likedCount = likedCount;
    }

    public long getOriginLikedCount() {
        return originLikedCount;
    }

    public void setOriginLikedCount(long originLikedCount) {
        this.originLikedCount = originLikedCount;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }

    public Date getFreeStarttime() {
        return freeStarttime;
    }

    public void setFreeStarttime(Date freeStarttime) {
        this.freeStarttime = freeStarttime;
    }

    public Date getFreeEndtime() {
        return freeEndtime;
    }

    public void setFreeEndtime(Date freeEndtime) {
        this.freeEndtime = freeEndtime;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTwoDeptInfo() {
        return twoDeptInfo;
    }

    public void setTwoDeptInfo(String twoDeptInfo) {
        this.twoDeptInfo = twoDeptInfo;
    }

    public String getSicknessInfo() {
        return sicknessInfo;
    }

    public void setSicknessInfo(String sicknessInfo) {
        this.sicknessInfo = sicknessInfo;
    }

    public String getDoctorInfo() {
        return doctorInfo;
    }

    public void setDoctorInfo(String doctorInfo) {
        this.doctorInfo = doctorInfo;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public Date getTopStarttime() {
        return topStarttime;
    }

    public void setTopStarttime(Date topStarttime) {
        this.topStarttime = topStarttime;
    }

    public Date getTopEndtime() {
        return topEndtime;
    }

    public void setTopEndtime(Date topEndtime) {
        this.topEndtime = topEndtime;
    }
}
