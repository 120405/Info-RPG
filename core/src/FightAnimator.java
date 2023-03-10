import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FightAnimator implements ApplicationListener {
    private SpriteBatch batch;
    private Texture hero, monster;
    private Animation<TextureRegion> heroAnim,monsterAnim;
    private TextureRegion currentFrame;
    private float stateTime;
    private Sprite heroSprite,monsterSprite;
    public FightAnimator() {
        create();
    }
    @Override
    public void create() {
    batch = new SpriteBatch();
        stateTime = 0f;
    hero = new Texture(Gdx.files.internal(""));
    monster = new Texture(Gdx.files.internal(""));
    heroAnim = createAnimRow(hero, 0, 0, 02.f, 0);
    monsterAnim = createAnimRow(monster, 0, 0, 02.f, 0);
    }

    @Override
    public void resize(int width, int height) {

    }
    public Animation<TextureRegion> createAnimRow(Texture t, int row, int col, float frameTime, int col1) {
        Animation<TextureRegion> Anim = null;
        TextureRegion[][] tmp = TextureRegion.split(t, t.getWidth() / col, t.getHeight() / row);
        TextureRegion[] TR = new TextureRegion[col * row];
        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                TR[index++] = tmp[col1][j];
            }
            Anim = new Animation<>(frameTime, TR);
        }
        return Anim;
    }
    @Override
    public void render() {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = heroAnim.getKeyFrame(stateTime, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
    batch.dispose();
    hero.dispose();
    monster.dispose();
    }
}
