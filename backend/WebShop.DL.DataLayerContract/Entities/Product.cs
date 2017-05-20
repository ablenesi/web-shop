namespace WebShop.DL.DataLayerContract.Entities
{
    public class Product : IEntity
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public decimal Price { get; set; }

        public virtual Category Category { get; set; }
    }
}
