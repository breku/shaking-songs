package com.brekol.manager;

import android.graphics.Color;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Breku
 * Date: 21.09.13
 */
public class ResourcesManager {

    private static final ResourcesManager INSTANCE = new ResourcesManager();
    private BaseGameActivity activity;
    private Engine engine;
    private Camera camera;
    private VertexBufferObjectManager vertexBufferObjectManager;

    private BitmapTextureAtlas splashTextureAtlas, menuFontTextureAtlas, gameFontTextureAtlas;
    private BuildableBitmapTextureAtlas menuTextureAtlas, optionsTextureAtlas, aboutTextureAtlas, endGameTextureAtlas,
            recordTextureAtlas, gameTypeTextureAtlas, gameTextureAtlas;

    // Game
    private ITextureRegion backgroundGameTextureRegion;

    // Splash
    private ITextureRegion splashTextureRegion;

    // Menu
    private ITextureRegion buttonAboutTextureRegion, buttonExitTextureRegion, buttonNewGameTextureRegion,
            buttonOptionsTextureRegion, menuBackgroundTextureRegion;

    // Help
    private ITextureRegion aboutTextureRegion, aboutBackgroundTextureRegion;

    // Options
    private ITextureRegion optionsBackgroundTextureRegion, optionsTextureRegion;

    // EndGame
    private ITextureRegion endGameBackgroundTextureRegion;

    // HighScore
    private ITextureRegion recordBackgroundTextureRegion, buttonHighScoreTextureRegion;

    // Game Type
    private ITextureRegion buttonClassicGameTextureRegion, buttonHalfmarathonGameTextureRegion,
            buttonMarathonGameTextureRegion, backgroundGameTypeTextureRegion;

    private List<Sound> winSoundList;
    private List<Sound> loseSoundList;
    private Font whiteFont, blackFont;


    public static void prepareManager(Engine engine, BaseGameActivity activity, Camera camera, VertexBufferObjectManager vertexBufferObjectManager) {
        getInstance().engine = engine;
        getInstance().activity = activity;
        getInstance().camera = camera;
        getInstance().vertexBufferObjectManager = vertexBufferObjectManager;
    }

    public void loadOptionsResources() {
        loadOptionsGraphics();
    }

    public void loadAboutResources() {
        loadAboutGraphics();
    }

    public void loadMainMenuResources() {
        loadMainMenuGraphics();
        loadMainMenuFonts();
    }

    public void loadGameResources() {
        loadGameGraphics();
//        loadGameFonts();
//        loadGameMusic();
//        loadEndGameResources();
    }

    public void loadEndGameResources() {
        loadEndGameGraphics();
    }

    public void loadRecordResources() {
        loadHighScoreGraphics();
    }

    public void loadGameTypeResources() {
        loadGameTypeGraphics();
    }

    private void loadGameTypeGraphics() {
        if (gameTypeTextureAtlas != null) {
            gameTypeTextureAtlas.load();
        }

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/gametype/");
        gameTypeTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);

        backgroundGameTypeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTypeTextureAtlas, activity, "background.jpg");
        buttonClassicGameTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTypeTextureAtlas, activity, "classic.png");
        buttonHalfmarathonGameTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTypeTextureAtlas, activity, "halfmarathon.png");
        buttonMarathonGameTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTypeTextureAtlas, activity, "marathon.png");

        try {
            gameTypeTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
            gameTypeTextureAtlas.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            e.printStackTrace();
        }
    }

    private void loadHighScoreGraphics() {
        if (recordTextureAtlas != null) {
            recordTextureAtlas.load();
        }

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/highscore/");

        recordTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 512, TextureOptions.BILINEAR);
        recordBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(recordTextureAtlas, activity, "background.jpg");

        try {
            recordTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
            recordTextureAtlas.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            e.printStackTrace();
        }


    }

    private void loadEndGameGraphics() {
        if (endGameTextureAtlas != null) {
            endGameTextureAtlas.load();
        }

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/endgame/");
        endGameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 512, TextureOptions.BILINEAR);

        endGameBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(endGameTextureAtlas, activity, "background.png");

        try {
            endGameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
            endGameTextureAtlas.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            e.printStackTrace();
        }
    }

    private void loadGameMusic() {

        SoundFactory.setAssetBasePath("mfx/other/");
        winSoundList = new ArrayList<Sound>();
        loseSoundList = new ArrayList<Sound>();
        try {
            winSoundList.add(SoundFactory.createSoundFromAsset(getEngine().getSoundManager(), activity, "win.ogg"));
            loseSoundList.add(SoundFactory.createSoundFromAsset(getEngine().getSoundManager(), activity, "lose.ogg"));
            loseSoundList.add(SoundFactory.createSoundFromAsset(getEngine().getSoundManager(), activity, "lose1.ogg"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void loadAboutGraphics() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/about/");
        aboutTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);

        aboutBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(aboutTextureAtlas, activity, "background.jpg");
        aboutTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(aboutTextureAtlas, activity, "about.png");

        try {
            aboutTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
            aboutTextureAtlas.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            e.printStackTrace();
        }
    }

    private void loadOptionsGraphics() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/options/");
        optionsTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);

        optionsBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(optionsTextureAtlas, activity, "background.jpg");
        optionsTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(optionsTextureAtlas, activity, "options.png");

        try {
            optionsTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
            optionsTextureAtlas.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            e.printStackTrace();
        }
    }


    private void loadGameGraphics() {

        if (gameTextureAtlas != null) {
            gameTextureAtlas.load();
        }

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");
        gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);

        backgroundGameTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "background.png");

        try {
            gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
            gameTextureAtlas.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            e.printStackTrace();
        }

    }

    private void loadMainMenuGraphics() {

        if (menuTextureAtlas != null) {
            menuTextureAtlas.load();
            return;
        }

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
        menuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);

        menuBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "background.jpg");
        buttonAboutTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "menu_help.png");
        buttonExitTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "menu_exit.png");
        buttonNewGameTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "menu_new.png");
        buttonOptionsTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "menu_options.png");
        buttonHighScoreTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "menu_high.png");

        try {
            menuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
            menuTextureAtlas.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            e.printStackTrace();
        }
    }

    public void loadSplashScreen() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        splashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
        splashTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, activity, "splash.jpg", 0, 0);
        splashTextureAtlas.load();
    }

    public void loadMenuTextures() {
        menuTextureAtlas.load();
    }

    private void loadGameFonts() {
        if (gameFontTextureAtlas != null) {
            gameFontTextureAtlas.load();
        }
        FontFactory.setAssetBasePath("font/");
        gameFontTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
        blackFont = FontFactory.createStrokeFromAsset(activity.getFontManager(), gameFontTextureAtlas, activity.getAssets(), "font1.ttf", 50, true, Color.BLACK, 2, Color.BLACK);
        gameFontTextureAtlas.load();
        blackFont.load();
    }


    private void loadMainMenuFonts() {
        if (menuFontTextureAtlas != null) {
            return;
        }
        FontFactory.setAssetBasePath("font/");
        menuFontTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
        whiteFont = FontFactory.createStrokeFromAsset(activity.getFontManager(), menuFontTextureAtlas, activity.getAssets(), "font2.ttf", 50, true, Color.WHITE, 2, Color.WHITE);
        menuFontTextureAtlas.load();
        whiteFont.load();
    }


    public void unloadSplashScreen() {
        splashTextureAtlas.unload();
        splashTextureRegion = null;
    }

    public void unloadOptionsTextures() {
        optionsTextureAtlas.unload();
    }

    public void unloadAboutTextures() {
        aboutTextureAtlas.unload();
    }

    public void unloadEndGameTextures() {
        endGameTextureAtlas.unload();
    }

    public void unloadRecordsTextures() {
        recordTextureAtlas.unload();
    }

    public void unloadGameTypeTextures() {
        gameTypeTextureAtlas.unload();
    }

    public void unloadGameTextures() {
        gameTextureAtlas.unload();
    }

    public void unloadMenuTextures() {
        menuTextureAtlas.unload();
    }


    public static ResourcesManager getInstance() {
        return INSTANCE;
    }

    public BaseGameActivity getActivity() {
        return activity;
    }

    public Engine getEngine() {
        return engine;
    }

    public Camera getCamera() {
        return camera;
    }

    public VertexBufferObjectManager getVertexBufferObjectManager() {
        return vertexBufferObjectManager;
    }

    public ITextureRegion getSplashTextureRegion() {
        return splashTextureRegion;
    }

    public ITextureRegion getButtonAboutTextureRegion() {
        return buttonAboutTextureRegion;
    }

    public ITextureRegion getButtonExitTextureRegion() {
        return buttonExitTextureRegion;
    }

    public ITextureRegion getButtonNewGameTextureRegion() {
        return buttonNewGameTextureRegion;
    }

    public ITextureRegion getButtonOptionsTextureRegion() {
        return buttonOptionsTextureRegion;
    }

    public ITextureRegion getMenuBackgroundTextureRegion() {
        return menuBackgroundTextureRegion;
    }

    public ITextureRegion getAboutTextureRegion() {
        return aboutTextureRegion;
    }

    public ITextureRegion getAboutBackgroundTextureRegion() {
        return aboutBackgroundTextureRegion;
    }

    public ITextureRegion getOptionsBackgroundTextureRegion() {
        return optionsBackgroundTextureRegion;
    }

    public ITextureRegion getOptionsTextureRegion() {
        return optionsTextureRegion;
    }

    public Font getWhiteFont() {
        return whiteFont;
    }

    public Font getBlackFont() {
        return blackFont;
    }

    public ITextureRegion getEndGameBackgroundTextureRegion() {
        return endGameBackgroundTextureRegion;
    }

    public ITextureRegion getRecordBackgroundTextureRegion() {
        return recordBackgroundTextureRegion;
    }

    public ITextureRegion getButtonHighScoreTextureRegion() {
        return buttonHighScoreTextureRegion;
    }

    public ITextureRegion getButtonClassicGameTextureRegion() {
        return buttonClassicGameTextureRegion;
    }

    public ITextureRegion getButtonHalfmarathonGameTextureRegion() {
        return buttonHalfmarathonGameTextureRegion;
    }

    public ITextureRegion getButtonMarathonGameTextureRegion() {
        return buttonMarathonGameTextureRegion;
    }

    public ITextureRegion getBackgroundGameTypeTextureRegion() {
        return backgroundGameTypeTextureRegion;
    }

    public List<Sound> getLoseSoundList() {
        return loseSoundList;
    }

    public List<Sound> getWinSoundList() {
        return winSoundList;
    }

    public ITextureRegion getBackgroundGameTextureRegion() {
        return backgroundGameTextureRegion;
    }
}
