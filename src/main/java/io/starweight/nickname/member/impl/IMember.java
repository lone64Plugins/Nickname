package io.starweight.nickname.member.impl;

import java.util.UUID;

public interface IMember {

    void init();

    UUID getUUID();

    String getOldName();

    String getNewName();

    void addMember(UUID uuid, String oldName, String newName);

    void delMember(UUID uuid);

    boolean isUUID(UUID uuid);

}