using System;
using System.Collections.Generic;

namespace WebShop.DL.DataLayerContract.Entities
{
    public class Order : IEntity
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Address { get; set; }
        public DateTime Date { get; set; }

        public virtual ICollection<OrderDetail> OrderDetails { get; set; }
    }
}
