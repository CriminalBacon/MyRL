package com.mygdx.game.entities;


import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.game.Enums;
import com.mygdx.game.Media;
import com.mygdx.game.Rumble;
import com.mygdx.game.box2d.Box2DHelper;
import com.mygdx.game.box2d.Box2DWorld;
import com.mygdx.game.entities.Entity;

public class Tree extends Entity {

    private Enums.ENTITYTYPE type;

    public Tree(Vector3 pos, Box2DWorld box2d){
        super();
        //type = Enums.ENTITYTYPE.TREE;
        setWidth(8);
        setHeight(8);
        setPos(pos);
        setTexture(Media.tree);
        setEntityType(Enums.ENTITYTYPE.TREE);

        setBody(Box2DHelper.createBody(box2d.world, getWidth()/2,
                getHeight()/2, getWidth()/4, 0,
                getPos(), BodyDef.BodyType.StaticBody));

        setSensor(Box2DHelper.createSensor(box2d.world, getWidth(), getHeight() * 0.85f,
                getWidth()/2, getHeight()/3, pos, BodyDef.BodyType.DynamicBody));

        setHashcode(getSensor().getFixtureList().get(0).hashCode());

    } //Tree

    @Override
    public void interact(Entity entity) {
        if(entity.getInventory() != null) {
            entity.getInventory().addEntity(this);
            setRemove(true);
            Rumble.rumble(1, 0.2f);
        }
    } //interact

} //class Tree
