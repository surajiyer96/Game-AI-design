/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import terrain.InfiniteGrid;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import utils.MultipleAnimationsController;

/**
 *
 * @author S.S.Iyer
 */
public class Basic3DTest extends ApplicationAdapter {
    
    final static float UNITS_PER_METER = 16f;
    final float HUMAN_HEIGHT = 3f*UNITS_PER_METER;
    final float[] FOG_COLOR = new float[] {0.13f, 0.13f, 0.13f, 1f};
    ModelBatch modelBatch;
    PerspectiveCamera camera;
    MultipleAnimationsController blueWalkController;
    MultipleAnimationsController blueIdleController;
    MultipleAnimationsController redWalkController;
    MultipleAnimationsController redIdleController;
    //dance
    MultipleAnimationsController redIdleController1;
    MultipleAnimationsController redIdleController2;
    MultipleAnimationsController redIdleController3;
    MultipleAnimationsController redIdleController4;
    MultipleAnimationsController redIdleController5;
    MultipleAnimationsController redIdleController6;
    MultipleAnimationsController redIdleController7;
    MultipleAnimationsController redIdleController8;
    MultipleAnimationsController redIdleController9;
    //end dance
    boolean fullscreen = false;
    boolean descendLimit = true;
    int oldWidth, oldHeight;
    ModelInstance skySphere;
    AssetManager assets;
    boolean assetLoading;
    Array<ModelInstance> instances;
    Environment environment;
    InfiniteGrid grid;
    
    @Override
    public void create() {
        // Misc.
        modelBatch = new ModelBatch();
        instances = new Array<>();
        assets = new AssetManager();
        
        // Set up an animation controller for the walking action of the blue player
        blueWalkController = new MultipleAnimationsController();
        blueWalkController.animationSpeed = 2f;
        
        //Set up an animation controller for the walking action of the red player
        redWalkController = new MultipleAnimationsController();
        redWalkController.animationSpeed = 2f;
        
        //Set up an animation controller for the idle action of the red player
        redIdleController = new MultipleAnimationsController();
        redIdleController.animationSpeed = 1f;
        
        //dance!
        //Set up an animation controller for the dance action of the red players
        redIdleController1 = new MultipleAnimationsController();
        redIdleController1.animationSpeed = 10f;
        //Set up an animation controller for the dance action of the red players
        redIdleController2 = new MultipleAnimationsController();
        redIdleController2.animationSpeed = 10f;
        //Set up an animation controller for the dance action of the red players
        redIdleController3 = new MultipleAnimationsController();
        redIdleController3.animationSpeed = 10f;
        //Set up an animation controller for the dance action of the red players
        redIdleController4 = new MultipleAnimationsController();
        redIdleController4.animationSpeed = 10f;
        //Set up an animation controller for the dance action of the red players
        redIdleController5 = new MultipleAnimationsController();
        redIdleController5.animationSpeed = 10f;
        //Set up an animation controller for the dance action of the red players
        redIdleController6 = new MultipleAnimationsController();
        redIdleController6.animationSpeed = 10f;
        //Set up an animation controller for the dance action of the red players
        redIdleController6 = new MultipleAnimationsController();
        redIdleController6.animationSpeed = 10f;
        //Set up an animation controller for the dance action of the red players
        redIdleController7 = new MultipleAnimationsController();
        redIdleController7.animationSpeed = 10f;
        //Set up an animation controller for the dance action of the red players
        redIdleController8 = new MultipleAnimationsController();
        redIdleController8.animationSpeed = 10f;
        //Set up an animation controller for the dance action of the red players
        redIdleController9 = new MultipleAnimationsController();
        redIdleController9.animationSpeed = 10f;
        //end dance
        
        
        // Set up an animation controller for the walking action of the blue player
        blueIdleController = new MultipleAnimationsController();
        blueIdleController.animationSpeed = 1f;
        
        // Set up a 3D perspective camera
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(18f*UNITS_PER_METER, 12f*UNITS_PER_METER, 0f);
        camera.lookAt(0,0,0);
        camera.near = 1.5f*UNITS_PER_METER;
        camera.far = 60f*UNITS_PER_METER;
        camera.up.set(Vector3.Y);
        camera.update();
        
        // load a 3d Model
        assets.load("tower/tower.g3db", Model.class);
        assets.load("flags/flagBlue.g3db", Model.class);
        assets.load("flags/flagNone.g3db", Model.class);
        assets.load("flags/flagRed.g3db", Model.class);
        assets.load("trees/tree1.g3db", Model.class);
        assets.load("trees/tree2.g3db", Model.class);
        assets.load("trees/tree3.g3db", Model.class);
        assets.load("trees/tree4.g3db", Model.class);
        assets.load("spacesphere/spacesphere.g3db", Model.class);
        assets.load("characters/BlueWalk.g3db", Model.class);
        assets.load("characters/BlueIdle.g3db", Model.class);
        assets.load("characters/RedWalk.g3db", Model.class);
        assets.load("characters/RedIdle.g3db", Model.class);
        assetLoading = true;
        
        // create a 3d box terrain
        grid = new InfiniteGrid(160, 160, UNITS_PER_METER);
        grid.instance.transform.setToTranslation(camera.position.x, 0, camera.position.z);
        
        // create the surrounding environment
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.set(new ColorAttribute(ColorAttribute.Fog, FOG_COLOR[0], 
                FOG_COLOR[1], FOG_COLOR[2], FOG_COLOR[3]));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
    }
    
    @Override
    public void render () {
        // Clear the color buffer and the depth buffer
        Gdx.gl.glClearColor(FOG_COLOR[0], FOG_COLOR[1], FOG_COLOR[2], FOG_COLOR[3]);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
        // Check for user input
        checkInput();
        
        // Set viewport
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        // Load assets once asset manager is done loading the asset
        if(assetLoading && assets.update()) {
            assetLoading();
        }
        
        // Update the camera controller
        if(descendLimit) {
            if(camera.position.y < grid.getHeight()+HUMAN_HEIGHT) {
                camera.position.set(camera.position.x, 
                        grid.getHeight()+HUMAN_HEIGHT, 
                        camera.position.z);
            }
        }
        
        // Render everything
//        blueWalkController.update();
//        redWalkController.update();
//        blueIdleController.update();
//        redIdleController.update();
//        
//        //dance
//        redIdleController1.update();
//        redIdleController2.update();
//        redIdleController3.update();
//        redIdleController4.update();
//        redIdleController5.update();
//        redIdleController6.update();
//        redIdleController7.update();
//        redIdleController8.update();
//        redIdleController9.update();
        //end dance
        modelBatch.begin(camera);
//        if (skySphere != null) {
//            skySphere.transform.setToTranslation(camera.position);
//            modelBatch.render(skySphere);
//        }
        modelBatch.render(instances, environment);
        //grid.updatePos(camera); // Move the grid with the camera for an infinte grid
        modelBatch.render(grid.instance);
        modelBatch.end();
    }
    
    @Override
    public void dispose () {
        modelBatch.dispose();
        instances.clear();
    }
    
    public void checkInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
           descendLimit = !descendLimit;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.F)) {
            if(!fullscreen) { // set resolution to default and set fullscreen to true
                oldWidth = Gdx.graphics.getWidth();
                oldHeight = Gdx.graphics.getHeight();
                Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width, 
                        Gdx.graphics.getDesktopDisplayMode().height, true);
            } else {
                Gdx.graphics.setDisplayMode(oldWidth, oldHeight, false);
            }
            fullscreen = !fullscreen;
        }
    }
    
    void assetLoading() {
        ModelInstance instance = new ModelInstance(assets.get("tower/tower.g3db", Model.class));
        instances.add(instance);
        instance = new ModelInstance(assets.get("trees/tree1.g3db", Model.class));
        instance.transform.setToTranslation(0, 0, -12*UNITS_PER_METER);
        instances.add(instance);
        instance = new ModelInstance(assets.get("trees/tree2.g3db", Model.class));
        instance.transform.setToTranslation(-12*UNITS_PER_METER, 0, -6*UNITS_PER_METER);
        instances.add(instance);
        instance = new ModelInstance(assets.get("trees/tree3.g3db", Model.class));
        instance.transform.setToTranslation(-12*UNITS_PER_METER, 0, 6*UNITS_PER_METER);
        instances.add(instance);
        instance = new ModelInstance(assets.get("trees/tree4.g3db", Model.class));
        instance.transform.setToTranslation(0, 0, 12*UNITS_PER_METER);
        instances.add(instance);
        instance = new ModelInstance(assets.get("flags/flagRed.g3db", Model.class));
        instance.transform.setToTranslation(6*UNITS_PER_METER, 0, -3f*UNITS_PER_METER);
        instances.add(instance);
        instance = new ModelInstance(assets.get("flags/flagNone.g3db", Model.class));
        instance.transform.setToTranslation(6*UNITS_PER_METER, 0, 0);
        instances.add(instance);
        instance = new ModelInstance(assets.get("flags/flagBlue.g3db", Model.class));
        instance.transform.setToTranslation(6*UNITS_PER_METER, 0, 3f*UNITS_PER_METER);
        
        instances.add(instance);
        instance = new ModelInstance(assets.get("characters/BlueIdle.g3db", Model.class));
        instance.transform.setToTranslation(8*UNITS_PER_METER, 0, 20*UNITS_PER_METER);
        blueIdleController.addAnimations(new String[] {
            "Head|HeadAction.002",
            "Torso|TorsoAction.001",
            "Right Hand|Right HandAction.002",
            "Left Hand|Left HandAction.001",
            "Right Leg|Right LegAction.001",
            "Left Leg|Left LegAction.001"}, instance);
        
        instances.add(instance);
        instance = new ModelInstance(assets.get("characters/BlueWalk.g3db", Model.class));
        instance.transform.setToTranslation(12*UNITS_PER_METER, 0, 20*UNITS_PER_METER);
        blueWalkController.addAnimations(new String[] {
            "Head|HeadAction",
            "Torso|TorsoAction",
            "Right Hand|Right HandAction",
            "Left Hand|Left HandAction",
            "Right Leg|Right LegAction",
            "Left Leg|Left LegAction"}, instance);
        
        instances.add(instance);
        instance = new ModelInstance(assets.get("characters/RedWalk.g3db", Model.class));
        instance.transform.setToTranslation(16*UNITS_PER_METER, 0, 20*UNITS_PER_METER);
        redWalkController.addAnimations(new String[] {
            "Head|HeadAction.001",
            "Torso|TorsoAction",
            "ArmRight|ArmRightAction",
            "ArmLeft|ArmLeftAction",
            "LegRight|LegRightAction",
            "LegLeft|LegLeftAction"}, instance);
        
        instances.add(instance);
        instance = new ModelInstance(assets.get("characters/RedIdle.g3db", Model.class));
        instance.transform.setToTranslation(20*UNITS_PER_METER, 0, 20*UNITS_PER_METER);
        redIdleController.addAnimations(new String[] {
            "Head|HeadAction",
            "Torso|TorsoAction.001",
            "ArmRight|ArmRightAction.001",
            "ArmLeft|ArmLeftAction.001",
            "LegRight|LegRightAction.001",
            "LegLeft|LegLeftAction.001"}, instance);
        
        
        
        
        
        
        //dance!!!
        instances.add(instance);
        instance = new ModelInstance(assets.get("characters/RedIdle.g3db", Model.class));
        instance.transform.setToTranslation(40*UNITS_PER_METER, 0, 40*UNITS_PER_METER);
        redIdleController1.addAnimations(new String[] {
            "Head|HeadAction",
            "Torso|TorsoAction.001",
            "ArmRight|ArmRightAction.001",
            "ArmLeft|ArmLeftAction.001",
            "LegRight|LegRightAction.001",
            "LegLeft|LegLeftAction.001"}, instance);
        
        instances.add(instance);
        instance = new ModelInstance(assets.get("characters/RedIdle.g3db", Model.class));
        instance.transform.setToTranslation(44*UNITS_PER_METER, 0, 40*UNITS_PER_METER);
        redIdleController2.addAnimations(new String[] {
            "Head|HeadAction",
            "Torso|TorsoAction.001",
            "ArmRight|ArmRightAction.001",
            "ArmLeft|ArmLeftAction.001",
            "LegRight|LegRightAction.001",
            "LegLeft|LegLeftAction.001"}, instance);
        
        instances.add(instance);
        instance = new ModelInstance(assets.get("characters/RedIdle.g3db", Model.class));
        instance.transform.setToTranslation(48*UNITS_PER_METER, 0, 40*UNITS_PER_METER);
        redIdleController3.addAnimations(new String[] {
            "Head|HeadAction",
            "Torso|TorsoAction.001",
            "ArmRight|ArmRightAction.001",
            "ArmLeft|ArmLeftAction.001",
            "LegRight|LegRightAction.001",
            "LegLeft|LegLeftAction.001"}, instance);
        
        instances.add(instance);
        instance = new ModelInstance(assets.get("characters/RedIdle.g3db", Model.class));
        instance.transform.setToTranslation(41*UNITS_PER_METER, 0, 44*UNITS_PER_METER);
        redIdleController4.addAnimations(new String[] {
            "Head|HeadAction",
            "Torso|TorsoAction.001",
            "ArmRight|ArmRightAction.001",
            "ArmLeft|ArmLeftAction.001",
            "LegRight|LegRightAction.001",
            "LegLeft|LegLeftAction.001"}, instance);
        
        instances.add(instance);
        instance = new ModelInstance(assets.get("characters/RedIdle.g3db", Model.class));
        instance.transform.setToTranslation(45*UNITS_PER_METER, 0, 44*UNITS_PER_METER);
        redIdleController5.addAnimations(new String[] {
            "Head|HeadAction",
            "Torso|TorsoAction.001",
            "ArmRight|ArmRightAction.001",
            "ArmLeft|ArmLeftAction.001",
            "LegRight|LegRightAction.001",
            "LegLeft|LegLeftAction.001"}, instance);
        
        instances.add(instance);
        instance = new ModelInstance(assets.get("characters/RedIdle.g3db", Model.class));
        instance.transform.setToTranslation(49*UNITS_PER_METER, 0, 44*UNITS_PER_METER);
        redIdleController6.addAnimations(new String[] {
            "Head|HeadAction",
            "Torso|TorsoAction.001",
            "ArmRight|ArmRightAction.001",
            "ArmLeft|ArmLeftAction.001",
            "LegRight|LegRightAction.001",
            "LegLeft|LegLeftAction.001"}, instance);
        
        instances.add(instance);
        instance = new ModelInstance(assets.get("characters/RedIdle.g3db", Model.class));
        instance.transform.setToTranslation(42*UNITS_PER_METER, 0, 48*UNITS_PER_METER);
        redIdleController7.addAnimations(new String[] {
            "Head|HeadAction",
            "Torso|TorsoAction.001",
            "ArmRight|ArmRightAction.001",
            "ArmLeft|ArmLeftAction.001",
            "LegRight|LegRightAction.001",
            "LegLeft|LegLeftAction.001"}, instance);
        
        instances.add(instance);
        instance = new ModelInstance(assets.get("characters/RedIdle.g3db", Model.class));
        instance.transform.setToTranslation(46*UNITS_PER_METER, 0, 48*UNITS_PER_METER);
        redIdleController8.addAnimations(new String[] {
            "Head|HeadAction",
            "Torso|TorsoAction.001",
            "ArmRight|ArmRightAction.001",
            "ArmLeft|ArmLeftAction.001",
            "LegRight|LegRightAction.001",
            "LegLeft|LegLeftAction.001"}, instance);
        
        instances.add(instance);
        instance = new ModelInstance(assets.get("characters/RedIdle.g3db", Model.class));
        instance.transform.setToTranslation(50*UNITS_PER_METER, 0, 48*UNITS_PER_METER);
        redIdleController9.addAnimations(new String[] {
            "Head|HeadAction",
            "Torso|TorsoAction.001",
            "ArmRight|ArmRightAction.001",
            "ArmLeft|ArmLeftAction.001",
            "LegRight|LegRightAction.001",
            "LegLeft|LegLeftAction.001"}, instance);
        //end dance
        
        instances.add(instance);
        skySphere = new ModelInstance(assets.get("spacesphere/spacesphere.g3db", Model.class));
        skySphere.transform.setToScaling(60*UNITS_PER_METER, 60*UNITS_PER_METER, 60*UNITS_PER_METER);
        assetLoading = false;
    }
}
