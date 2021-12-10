package com.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.entity.AttendEntity;
import com.project.entity.FriendshipEntity;
import com.project.entity.Loginvo;
import com.project.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserService extends IService<UserEntity> {
    Map<String, Object> loginUser(Loginvo loginvo);
    Boolean registerUser(Loginvo loginvo);
    Map<String, Object> loginByName(Loginvo loginvo);

    List<FriendshipEntity> findFriend(Long userid);
    List<AttendEntity> findCompetition(Long userid);

    boolean addFriend(Long userid, Long friendid);

    boolean deleteFriend(Long userid, Long friendid);

    boolean changeFriendStatus(Long userid, Long friendid);

    List<FriendshipEntity> findfriendRequest(Long userid);
}
