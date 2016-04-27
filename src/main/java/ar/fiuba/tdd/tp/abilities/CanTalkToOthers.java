package ar.fiuba.tdd.tp.abilities;

import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeTalkedTo;

public interface CanTalkToOthers {
    String talkTo(GameObjectCanBeTalkedTo objectThatIsTalkedTo);
}
