package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.Invitation;

import java.util.List;

public interface InvitationService extends IService<Invitation> {
    public int createInvitation(Invitation invitation);
    public List<Invitation> getInvitations(String invitedID);
    public int updateInvitation(Invitation invitation);
}
