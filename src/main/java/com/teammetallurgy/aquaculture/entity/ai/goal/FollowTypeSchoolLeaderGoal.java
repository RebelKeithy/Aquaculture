package com.teammetallurgy.aquaculture.entity.ai.goal;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;

import java.util.List;
import java.util.function.Predicate;

public class FollowTypeSchoolLeaderGoal extends Goal {
    private final AbstractGroupFishEntity taskOwner;
    private int navigateTimer;
    private int field_222740_c;

    public FollowTypeSchoolLeaderGoal(AbstractGroupFishEntity fishEntity) {
        this.taskOwner = fishEntity;
        this.field_222740_c = this.func_212825_a(fishEntity);
    }

    private int func_212825_a(AbstractGroupFishEntity fishEntity) {
        return 200 + fishEntity.getRNG().nextInt(200) % 20;
    }

    @Override
    public boolean shouldExecute() {
        if (this.taskOwner.func_212812_dE()) {
            return false;
        } else if (this.taskOwner.func_212802_dB()) {
            return true;
        } else if (this.field_222740_c > 0) {
            --this.field_222740_c;
            return false;
        } else {
            this.field_222740_c = this.func_212825_a(this.taskOwner);
            Predicate<AbstractGroupFishEntity> predicate = (fishEntity) -> fishEntity.getType() == this.taskOwner.getType() && (fishEntity.func_212811_dD() || !fishEntity.func_212802_dB());
            List<AbstractGroupFishEntity> schoolList = this.taskOwner.world.getEntitiesWithinAABB(this.taskOwner.getClass(), this.taskOwner.getBoundingBox().grow(8.0D, 8.0D, 8.0D), predicate);
            AbstractGroupFishEntity fishEntity = schoolList.stream().filter(AbstractGroupFishEntity::func_212811_dD).findAny().orElse(this.taskOwner);
            fishEntity.func_212810_a(schoolList.stream().filter((schoolFish) -> !schoolFish.func_212802_dB()));
            return this.taskOwner.func_212802_dB();
        }
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.taskOwner.func_212802_dB() && this.taskOwner.func_212809_dF();
    }

    @Override
    public void startExecuting() {
        this.navigateTimer = 0;
    }

    @Override
    public void resetTask() {
        this.taskOwner.func_212808_dC();
    }

    @Override
    public void tick() {
        if (--this.navigateTimer <= 0) {
            this.navigateTimer = 10;
            this.taskOwner.func_212805_dG();
        }
    }
}