package ssm.dao;

import entity.Member;
import org.apache.ibatis.annotations.Select;

public interface MemberDao {
    @Select("select * from member where id=#{MemberId}")
    public Member findById(String MemberId);

}
