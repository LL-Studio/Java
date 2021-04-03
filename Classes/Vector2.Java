package Modules;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Vector2 {
    public float X, Y;

    public Vector2() {
        this.X = 0;
        this.Y = 0;
    }

    public Vector2(float X, float Y)  {
        this.X = X;
        this.Y = Y;
    }

    public Vector2(Vector2 Other) {
        this.X = Other.X;
        this.Y = Other.Y;
    }

    public final boolean equals(Vector2 Other) {
        return (boolean) (Other.X == this.X && Other.Y == this.Y);
    }

    public final Vector2 add(float Amount) {
        Vector2 NewVector = new Vector2(this);
        NewVector.X += Amount;
        NewVector.Y += Amount;
        return NewVector;
    }

    public final Vector2 add(Vector2 Other) {
        Vector2 NewVector = new Vector2(this);
        NewVector.X += Other.X;
        NewVector.Y += Other.Y;
        return NewVector;
    }

    public final Vector2 sub (float Amount) {
        Vector2 NewVector = new Vector2(this);
        NewVector.X -= Amount;
        NewVector.Y -= Amount;
        return NewVector;
    }

    public final Vector2 sub(Vector2 Other) {
        Vector2 NewVector = new Vector2(this);
        NewVector.X -= Other.X;
        NewVector.Y -= Other.Y;
        return NewVector;
    }

    public final Vector2 mul(float Scale) {
        Vector2 NewVector = new Vector2(this);
        NewVector.X *= Scale;
        NewVector.Y *= Scale;
        return NewVector;
    }

    public final Vector2 mul(Vector2 Scale) {
        Vector2 NewVector = new Vector2(this);
        NewVector.X *= Scale.X;
        NewVector.Y *= Scale.Y;
        return NewVector;
    }

    public final float Magnitude() {
        return (float) Math.sqrt(Math.pow(this.X, 2) + Math.pow(this.Y, 2));
    }

    public final Vector2 Unit() {
        Vector2 NewVector = new Vector2(this);
        float Mag = NewVector.Magnitude();
        if(Mag != 0) {
            NewVector.X /= Mag;
            NewVector.Y /= Mag;
        }
        return NewVector;
    }

    public final String toString() {
        return "(" + this.X + ", " + this.Y + ")";
    }
}
