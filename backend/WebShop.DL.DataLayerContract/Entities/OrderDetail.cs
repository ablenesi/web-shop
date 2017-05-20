namespace WebShop.DL.DataLayerContract.Entities
{
    public class OrderDetail : IEntity
    {
        public int OrderId { get; set; }
        public int ProductId { get; set; }
        public decimal Quantity { get; set; }

        public virtual Product Product { get; set; }
    }
}
