using System;
using System.Collections.Generic;
using System.ServiceModel;

using WebShop.BL.BusinessLayerContracts.DTOs;
using WebShop.SL.ServiceLayerContracts;

namespace WebShop.SL.Client
{
    public class OrderClient : ClientBase<IOrderService>, IOrderService
    {
        public void Create(OrderDTO order)
        {
            Channel.Create(order);
        }

        public IEnumerable<OrderDTO> GetAll()
        {
            return Channel.GetAll();
        }
    }
}
