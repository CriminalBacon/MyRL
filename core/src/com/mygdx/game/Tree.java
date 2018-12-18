package com.mygdx.game;


import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.game.box2d.Box2DHelper;
import com.mygdx.game.box2d.Box2DWorld;

public class Tree extends Entity {

    private Enums.ENTITYTYPE type;

    public Tree(Vector3 pos, Box2DWorld box2d){
        super();
        type = Enums.ENTITYTYPE.TREE;
        setWidth(8);
        setHeight(8);
        setPos(pos);
        setTexture(Media.tree);

        setBody(Box2DHelper.createBody(box2d.world, getWidth()/2,
                getHeight()/2, getWidth()/4, 0,
                getPos(), BodyDef.BodyType.StaticBody));

    } //Tree



} //class Tree
