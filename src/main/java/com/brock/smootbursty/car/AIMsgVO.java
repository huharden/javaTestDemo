package com.brock.smootbursty.car;

/**
 * description: 接收智能回答返回数据
 * @author: hutao
 * @date 2019/8/10 11:50
 */
public class AIMsgVO {

    private String type;

    private String student_id;

    private String student_pic;

    private String teacher_id;

    private String teacher_pic;

    private String topic_id;

    private String content;

    private String title_content;

    private String score_1;

    private String score_2;

	private String highlight;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getScore_1() {
        return score_1;
    }

    public void setScore_1(String score_1) {
        this.score_1 = score_1;
    }

    public String getScore_2() {
        return score_2;
    }

    public void setScore_2(String score_2) {
        this.score_2 = score_2;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public String getStudent_pic() {
        return student_pic;
    }

    public void setStudent_pic(String student_pic) {
        this.student_pic = student_pic;
    }

    public String getTeacher_pic() {
        return teacher_pic;
    }

    public void setTeacher_pic(String teacher_pic) {
        this.teacher_pic = teacher_pic;
    }

    public String getTitle_content() {
        return title_content;
    }

    public void setTitle_content(String title_content) {
        this.title_content = title_content;
    }

    @Override
    public String toString() {
        return "AIMsgVO{" +
                "type='" + type + '\'' +
                ", student_id='" + student_id + '\'' +
                ", student_pic='" + student_pic + '\'' +
                ", student_id='" + student_id + '\'' +
                ", teacher_pic='" + teacher_pic + '\'' +
                ", topic_id='" + topic_id + '\'' +
                ", content=" + content +
                ", title_content=" + title_content +
                ", score_1='" + score_1 + '\'' +
                ", score_2='" + score_2 + '\'' +
                ", highlight=" + highlight +
                '}';
    }
}
