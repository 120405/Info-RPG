package com.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class DragandDrop {
    public void DragandDrop(final Stage stage, final Window window, final Image img) {
    final DragAndDrop dragdrop = new DragAndDrop();
    window.add(img);
    img.setPosition(500, 500);
    img.setUserObject(window);
    dragdrop.addSource(new DragAndDrop.Source(img) {
        @Override
        public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
            DragAndDrop.Payload payload = new DragAndDrop.Payload();
            payload.setDragActor(getActor());
            stage.addActor(getActor());
            dragdrop.setDragActorPosition(getActor().getWidth()/2, getActor().getHeight()/ 2);
            return payload;
        }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target)     {
            if(target == null) {
                ((Window)img.getUserObject()).add(img);
               System.out.println("test");
            }
    }
    });
    dragdrop.addTarget(new DragAndDrop.Target(window) {
        @Override
        public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
            return true;
        }

        @Override
        public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
            ((Image)(payload.getDragActor())).setPosition(x, y);
            window.add((Image)(payload.getDragActor()));
            img.setUserObject(window);
        }
    });
    dragdrop.addTarget(new DragAndDrop.Target(window) {
        @Override
        public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
            return true;
        }

        @Override
        public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
            ((Image)(payload.getDragActor())).setPosition(x, y);
                window.add((Image)(payload.getDragActor()));
                img.setUserObject(window);
        }
    });
    }
}
