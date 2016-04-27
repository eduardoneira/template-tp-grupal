package ar.fiuba.tdd.tp.abilities;

import ar.fiuba.tdd.tp.objects.general.GameObjectCanTalkToOthers;

public interface CanBeTalkedTo {
    String beTalkedToBy(GameObjectCanTalkToOthers objectThatTalks);
}
