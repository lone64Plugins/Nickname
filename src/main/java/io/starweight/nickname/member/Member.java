package io.starweight.nickname.member;

import io.slib.library.sqlite.SQLite;
import io.starweight.nickname.Nickname;
import io.starweight.nickname.member.impl.IMember;

import java.util.UUID;

public class Member implements IMember {

    private SQLite sqlite;

    private final UUID uuid;

    public Member() {
        this.sqlite = new SQLite(Nickname.getPlugin(), "cache", "member.db");
        this.uuid = null;
    }

    public Member(UUID uuid) {
        this.sqlite = new SQLite(Nickname.getPlugin(), "cache", "member.db");
        this.uuid = uuid;
    }

    @Override
    public void init() {
        this.sqlite = new SQLite(Nickname.getPlugin(), "cache", "member.db");
        this.sqlite.setTable("nickname_cache", "uuid VARCHAR(36), oldName VARCHAR(12), newName VARCHAR(12)");
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public String getOldName() {
        return this.sqlite.getString("nickname_cache", "uuid", "oldName", getUUID().toString());
    }

    @Override
    public String getNewName() {
        return this.sqlite.getString("nickname_cache", "uuid", "newName", getUUID().toString());
    }

    @Override
    public void addMember(UUID uuid, String oldName, String newName) {
        this.sqlite.insert("nickname_cache", "uuid, oldName, newName", "?, ?, ?", uuid.toString(), oldName, newName);
    }

    @Override
    public void delMember(UUID uuid) {
        this.sqlite.delete("nickname_cache", "uuid", uuid.toString());
    }

    @Override
    public boolean isUUID(UUID uuid) {
        return this.sqlite.exists("nickname_cache", "uuid", uuid.toString());
    }

}