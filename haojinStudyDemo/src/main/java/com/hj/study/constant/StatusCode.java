package com.hj.study.constant;

/**
 * 业务状态码
 *
 * @author : hutao
 * @date : 2017/8/5 18:25
 */
public enum StatusCode {
    FAILURE(-1, "失败"),
    NO_EXIT(1, "查询数据不存在"),
    SUCCESS(200, "成功"),
    SYS_ERROR(500,"系统错误"),
    PARAMETER_ERROR(1001, "提交参数不符合规范"),
    USERTOKEN_PARAMETER_ERROR(1002, "Token参数错误"),
    PARAMETER_MISSING(1003, "缺失必要参数"),
    LOGIN_FAILURE(1110, "登录失败"),
    LOGIN_EXCESSIVE_ATTEMPTS(1111, "登录失败次数太多，请30分钟后再试"),
    LOGIN_VERIFYCODE_ERROR(1112, "验证码错误"),
    LOGIN_PASSWORD_ERROR(1113, "用户密码错误"),
    LOGIN_USER_NOEXIST(1114, "用户不存在"),
    LOGIN_USER_LOCK(1115, "此用户已被禁用"),
    LOGIN_USER_DELETE(1116, "此用户已被删除"),
    LOGIN_ACCOUNT_LOCK(1117, "企业账户被禁用"),
    USER_UNAUTHORIZED(1118, "用户无权限进行此操作"),
    PRIVATE_UNAUTHORIZED(1119, "非法请求"),
    CAN_NOT_INSTANCE_CONSTANT(1120,"禁止示例常量"),
    TOKEN_OVERDUE(1130, "登录过期，请重新登录"),
    TOKEN_OUT(1131, "登录失效，帐号在其他地方登录"),
    TOKEN_FAULT(1132, "未登录，无权限访问"),
    WRONG_IP_FORMAT(1133,"错误的IP格式"),
    IP_EXIST(1134,"数据库中已存在该记录"),
    IP_REJECTED(1135,"禁止此IP访问！"),
    IP_COUNT_REJECTED(1136,"增加屏蔽次数失败"),
    EXPORT_ERROR(1136,"导出范围不能超出三个月"),
    DATE_NOT_BANK(1136,"时间不能为空"),
    WRONG_IP_INFORMATION(1137,"黑名单录入失败"),
    WRONG_SUPERADMIN_ERRO(1138,"不支持创建超级管理员用户"),
    WRONG_PHONE_ERRO(1139,"手机号码不能为空"),
    WRONG_ROLEUPDATE_ERRO(1140,"当前用户不能使用或修改该角色"),
    REDIS_ATYPISM(1201, "缓存数据与数据库数据不一致"),
    DATABASE_DUPLICATEKEY(2001, "数据库中已存在该记录"),
    DATABASE_SAVE_FAILURE(2002, "添加失败"),
    DATABASE_UPDATE_FAILURE(2003, "修改失败"),
    DATABASE_DELETE_FAILURE(2004, "删除失败"),
    DATABASE_SELECT_FAILURE(2005, "资源不存在"),
    DATABASE_NOT_CHANGE(2006, "未作任何修改"),
    DATABASE_SELECT_USE(2007, "该数据在被使用中，不允许删除"),
    DATABASE_SELECT_EXIST_USE(2008, "该数据存在关联数据，不允许删除"),
    DATABASE_SELECT_EXIST_CODE(2009, "该编码已存在，不允许重复"),
    DATABASE_UPDATE_ROOT(2101, "根节点，不允许修改"),
    DATABASE_DELETE_ROOT(2102, "根节点，不允许删除"),
    DATABASE_UPDATE_CHILD(2103, "存在子节点，不允许修改"),
    DATABASE_DELETE_CHILD(2104, "存在子节点，不允许删除"),
    DATABASE_SAVE_GOOD_LESSON_FULL(2105, "数量已满"),
    PERMISSION_UNAUTHORIZED(2201, "不允许越权操作"),
    PERMISSION_ONESELF(2202, "不允许对自己进行操作"),
    HAVE_ACCOUNT(2304, "该类目存在资讯或爬取的资讯，请全部删除后再操作"),
    UNDERCARRIGE(2305, "该资讯已下架"),
    NAME_EXIST(2306, "该类目已存在"),
    NO_DATA(2307, "查询结果为空"),
    BIZ_IMPORT_LIMIT(2401, "导入条数超过限制"),
    BIZ_IMPORT_EMPTY(2402, "导入数据不能为空"),
    FILE_DAMAGED(2403, "导入文件损坏"),
    API_CALL_FAILED(3001, "接口调用失败"),
    IMPORT_EXCEL_EXCEPTION(5000, "导入文件格式不正确，请下载指定模板编写再导入"),
    EXPORT_EXCEL_EXCEPTION(5001, "导出报表数据处理异常"),
    DATA_DISABLED(6001,"数据中存在已被禁止的敏感词"),
    DATA_HIDE(6002,"数据中部分敏感词已经屏蔽"),
    DATABASE_EXIST(6003,"该敏感词已经存在"),
    DATA_PART_EXIST(6004,"部分敏感词已经存在"),
    DATA_EXIST_SENSITIVE_WORD(6005,"存在敏感词"),
    NETWORK_FAILURE(8001,"网络异常，请重试"),
    EMAIL_FAILURE(8002,"发送失败，请重新发送");


    private final int code;
    private final String msg;

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
