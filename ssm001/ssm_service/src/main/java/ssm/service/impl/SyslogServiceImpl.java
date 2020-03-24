package ssm.service.impl;

import entity.Syslog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm.dao.SyslogDao;
import ssm.service.SyslogService;

@Service
@Transactional(rollbackFor = Exception.class)
public class SyslogServiceImpl implements SyslogService {
    @Autowired
    private SyslogDao syslogDao;

    @Override
    public void save(Syslog syslog) {
        syslogDao.save(syslog);
    }
}
