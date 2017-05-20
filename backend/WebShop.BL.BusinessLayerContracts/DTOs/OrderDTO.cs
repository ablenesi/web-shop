using System;
using System.Collections.Generic;

namespace WebShop.BL.BusinessLayerContracts.DTOs
{
    public class OrderDTO
        {
            public int Id { get; set; }
            public string Name { get; set; }
            public string Address { get; set; }
            public DateTime Date { get; set; }
            public ICollection<OrderDetailDTO> OrderDetails { get; set; }
    }
}