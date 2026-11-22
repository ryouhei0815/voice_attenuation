package com.ryouhei.Voice.Mixin;

import de.maxhenkel.voicechat.voice.client.AudioChannel;
import de.maxhenkel.voicechat.voice.client.ClientVoicechat;
import de.maxhenkel.voicechat.voice.common.PlayerSoundPacket;
import de.maxhenkel.voicechat.voice.common.SoundPacket;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = AudioChannel.class,remap = false)
public abstract class VoiceFactor {


    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow
    @Final
    private ClientVoicechat client;

    @ModifyVariable(
            method = "writeToSpeaker(Lde/maxhenkel/voicechat/voice/common/SoundPacket;[S)V",
            at = @At("STORE"),
            ordinal = 0
    )
    private float adjustVolumeByDistance(float volume, SoundPacket<?> packet) {
        if (packet instanceof PlayerSoundPacket soundPacket) {
            double dis = (this.client.getInitializationData() == null) ? 48 : this.client.getInitializationData().getVoiceChatDistance();
            float distance = soundPacket.getDistance();
            double distanceFactor = calculateDistanceVolume(distance, (float) dis);
            // 逆二乗減衰に近い計算e
            return (float) (volume * distanceFactor);
        } else {
            return volume;
        }
    }

    private float calculateDistanceVolume(float distance, float maxDistance) {

        float ratio = distance / maxDistance;
        return ratio*ratio;
    }
}




