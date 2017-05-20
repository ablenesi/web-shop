namespace WebShop.BL.BusinessLayerContracts.DTOs
{
    public class OrderDetailDTO
    {
        public int ProductId { get; set; }
        public int OrderId { get; set; }
        public decimal Quantity { get; set; }
    }
}
