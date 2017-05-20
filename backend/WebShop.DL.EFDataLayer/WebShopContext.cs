using System.Data.Entity;

using WebShop.DL.DataLayerContract.Entities;

namespace WebShop.DL.EFDataLayer
{
    public class WebShopContext : DbContext
    {
        public WebShopContext()
            : base("name=WebShop")
        { }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<OrderDetail>().HasKey(x => 
                new 
                { 
                    ProductId = x.ProductId,
                    OrderId = x.OrderId
                });
        }

        public DbSet<Category> Categories { get; set; }
        public DbSet<Order> Orders { get; set; }
        public DbSet<OrderDetail> OrderDetails { get; set; }
        public DbSet<Product> Products { get; set; }
    }
}
