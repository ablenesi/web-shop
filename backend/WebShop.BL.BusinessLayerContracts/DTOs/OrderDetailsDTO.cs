using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WebShop.BL.BusinessLayerContracts.DTOs
{
    public class OrderDetailsDTO
    {
        public int ProductId { get; set; }
        public int OrderId { get; set; }
        public decimal Quantity { get; set; }
    }
}
