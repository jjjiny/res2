package ssm.dao;

import entity.Syslog;
import org.apache.ibatis.annotations.Insert;

public interface SyslogDao {
    @Insert("insert into syslog(visitTime,username,ip,url666,executionTime,method) values(# {visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(Syslog syslog);
}
