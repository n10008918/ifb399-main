package com.example.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.InvitationDao;
import com.example.domain.Invitation;
import com.example.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationServicesImp extends ServiceImpl<InvitationDao, Invitation> implements InvitationService {
    @Autowired
    private InvitationDao invitationDao;

    @Override
    public int createInvitation(Invitation invitation) {
        int count = invitationDao.insert(invitation);
        return count;
    }

    @Override
    public List<Invitation> getInvitations(String id) {//notifyId or invitedId
        LambdaQueryWrapper<Invitation> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Invitation::getInvitedId, id).or();
        queryWrapper.eq(Invitation::getNotifyId, id);
       ;

//        QueryWrapper<Invitation> queryWrapper = new QueryWrapper();
//        queryWrapper.eq("invited_id", id);
        return invitationDao.selectList(queryWrapper);
    }

    @Override
    public int updateInvitation(Invitation invitation) {
        return invitationDao.updateById(invitation);
    }

}
