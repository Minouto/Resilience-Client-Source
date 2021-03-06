package net.minecraft.world.gen.layer;

public class GenLayerZoom extends GenLayer
{
    private static final String __OBFID = "CL_00000572";

    public GenLayerZoom(long par1, GenLayer par3GenLayer)
    {
        super(par1);
        super.parent = par3GenLayer;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int var5 = par1 >> 1;
        int var6 = par2 >> 1;
        int var7 = (par3 >> 1) + 2;
        int var8 = (par4 >> 1) + 2;
        int[] var9 = this.parent.getInts(var5, var6, var7, var8);
        int var10 = var7 - 1 << 1;
        int var11 = var8 - 1 << 1;
        int[] var12 = IntCache.getIntCache(var10 * var11);
        int var14;

        for (int var13 = 0; var13 < var8 - 1; ++var13)
        {
            var14 = (var13 << 1) * var10;
            int var15 = 0;
            int var16 = var9[var15 + 0 + (var13 + 0) * var7];

            for (int var17 = var9[var15 + 0 + (var13 + 1) * var7]; var15 < var7 - 1; ++var15)
            {
                this.initChunkSeed((long)(var15 + var5 << 1), (long)(var13 + var6 << 1));
                int var18 = var9[var15 + 1 + (var13 + 0) * var7];
                int var19 = var9[var15 + 1 + (var13 + 1) * var7];
                var12[var14] = var16;
                var12[var14++ + var10] = this.func_151619_a(new int[] {var16, var17});
                var12[var14] = this.func_151619_a(new int[] {var16, var18});
                var12[var14++ + var10] = this.func_151617_b(var16, var18, var17, var19);
                var16 = var18;
                var17 = var19;
            }
        }

        int[] var20 = IntCache.getIntCache(par3 * par4);

        for (var14 = 0; var14 < par4; ++var14)
        {
            System.arraycopy(var12, (var14 + (par2 & 1)) * var10 + (par1 & 1), var20, var14 * par3, par3);
        }

        return var20;
    }

    /**
     * Magnify a layer. Parms are seed adjustment, layer, number of times to magnify
     */
    public static GenLayer magnify(long par0, GenLayer par2GenLayer, int par3)
    {
        Object var4 = par2GenLayer;

        for (int var5 = 0; var5 < par3; ++var5)
        {
            var4 = new GenLayerZoom(par0 + (long)var5, (GenLayer)var4);
        }

        return (GenLayer)var4;
    }
}
